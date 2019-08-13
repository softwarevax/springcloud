package com.wit.sc.support.configuration.plugins.aspect;

import com.wit.sc.support.configuration.support.SupportConstant;
import com.wit.sc.support.web.domain.Constant;
import com.wit.sc.support.web.domain.MethodRecordBo;
import com.wit.sc.support.web.domain.Paramter;
import com.wit.sc.support.web.service.InterfaceHandler;
import com.wit.sc.support.web.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.config.aspect
 * @Description: 请求属性切面
 * @date 2019/6/13 21:43
 */
@Aspect
@Component
public class MethodAspect {

    public final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss:SSS";

    ThreadLocal<Stack<MethodRecordBo>> methodRedcord = new ThreadLocal();

    @Autowired
    MethodAspectHandler handler;

    @Autowired
    InterfaceHandler interfaceHandler;

    @Autowired
    SupportConstant constant;

    /**
     * 定义切入点
     * 切点不能包含当切面方法，否则造成无限循环
     */
    @Pointcut("execution(* com.wit.sc.*.web..*.*(..)) && !execution(* com.wit.sc.support.web..*.*(..))")
    public void methodPointcut(){}

    /**
     * 前置通知：在连接点之前执行的通知
     * @param point
     * @throws Throwable
     */
    @Before("methodPointcut()")
    public void doBefore(JoinPoint point) {
        if(constant.getMethod().isDetailRecordEnable()) {
            recordMethodDetail(point);
        }
        if(constant.getMethod().isArgsRrecordEnable()) {
            printLoggerDetails(point);
        }
    }

    @AfterReturning(returning = "ret",pointcut = "methodPointcut()")
    public void doAfterReturning(Object ret) {
        if(constant.getMethod().isDetailRecordEnable()) {
            insertRecordMethodDetail(ret);
        }
    }

    /**
     * 记录方法详情(入库)
     * @param point
     */
    private void recordMethodDetail(JoinPoint point) {
        Signature signature = point.getSignature();
        MethodRecordBo record = new MethodRecordBo();
        record.setMethodId(CommonUtils.uuid());
        record.setStartTime(System.currentTimeMillis());
        record.setThreadName(Thread.currentThread().getName());

        record.setTrackId(StringUtils.EMPTY);
        //设置接口方法相关参数
        initInterfaceDetail(point, record);

        record.setMethodName(signature.getName());
        record.setClassName(signature.getDeclaringTypeName());
        List<Paramter> paramters = new ArrayList<>();
        Object[] args = point.getArgs();
        String argId = CommonUtils.uuid();
        record.setArgId(argId);
        for (int i = 0, len = args.length; i < len; i++) {
            Object obj = args[i];
            if(null == obj) {
                continue;
            }
            Paramter paramter = new Paramter();
            paramter.setObjId(argId);
            paramter.setId(CommonUtils.uuid());
            paramter.setObjValue(obj.toString());
            paramter.setObjIdx(i + 1);
            paramters.add(paramter);
        }
        record.setArgs(paramters);
        Stack<MethodRecordBo> stack = methodRedcord.get();
        if(CollectionUtils.isEmpty(stack)) {
            stack = new Stack<>();
        }
        stack.push(record);
        methodRedcord.set(stack);
    }

    /**
     * 方法详情入库
     */
    private void insertRecordMethodDetail(Object ret) {
        Stack<MethodRecordBo> stack = methodRedcord.get();
        if(stack == null) {
            return;
        }
        MethodRecordBo record = stack.pop();
        if(record == null) {
            return;
        }
        record.setEndTime(System.currentTimeMillis());
        String resultId = CommonUtils.uuid();
        record.setResultId(resultId);
        Paramter paramter = new Paramter();
        paramter.setObjId(resultId);
        paramter.setId(CommonUtils.uuid());
        paramter.setObjIdx(1);
        if(ret != null) {
            paramter.setObjValue(ret.toString());
        }
        record.setResult(Arrays.asList(paramter));
        handler.methodHandle(record);
    }

    /**
     * 打印日志详情(输出到控制台)
     * @param point
     */
    private void printLoggerDetails(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        Signature signature = point.getSignature();
        // 1、时间
        String timeStamp = DateFormatUtils.format(new Date(), DATE_FORMATE);
        // 2、类名
        String className = signature.getDeclaringTypeName();
        // 3、接口名
        String interfaceName = request.getRequestURI();

        String signatureName = signature.toString();
        String methodName = StringUtils.substringBetween(signatureName, "(", ")");
        String[] argTypes = StringUtils.split(methodName, ",");
        // 4、方法参数
        Object[] args = point.getArgs();

        // 5、contextPath
        String contextPath = request.getServletContext().getContextPath();

        StringBuffer loggerDetails = new StringBuffer(120);
        loggerDetails.append(timeStamp);
        loggerDetails.append(" ");
        loggerDetails.append(contextPath);
        loggerDetails.append(" ");
        loggerDetails.append(className);
        loggerDetails.append(".");
        loggerDetails.append(point.getSignature().getName());
        loggerDetails.append("(");

        for (int i = 0, len = argTypes.length; i < len; i++) {
            loggerDetails.append(argTypes[i]).append("[").append(args[i]).append("]").append(",");
        }
        int lastIndex = loggerDetails.lastIndexOf(",");
        if(lastIndex > -1) {
            loggerDetails.deleteCharAt(lastIndex);
        }
        loggerDetails.append(")");
        System.out.println(loggerDetails.toString());
    }

    private void initInterfaceDetail(JoinPoint point, MethodRecordBo record) {
        Signature signature = point.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String methodName = signature.getName();
        if(interfaceHandler.isInterface(request.getServletContext(), methodName)) {
            // 只有接口请求才会有以下参数
            record.setUserAgent(request.getHeader(Constant.REQUEST_USER_AGENT));
            record.setServerHost(request.getHeader(Constant.REQUEST_HOST));
            record.setUrl(request.getRequestURL().toString());
            record.setMethodType(request.getMethod());
            record.setClientHost(request.getRemoteAddr());
            record.setTrackId(request.getHeader(Constant.REQUEST_TRACK_ID));
        }
    }
}
