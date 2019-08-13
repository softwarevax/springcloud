package com.wit.sc.support.configuration.plugins.aspect;

import com.wit.sc.support.configuration.support.SupportConstant;
import com.wit.sc.support.web.domain.ResultDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.config.aspect
 * @Description:先进入GlobalAdviceController切面,@Order中的值越小，优先级越高,此切面要求最后一个执行
 * @date 2019/6/16 11:38
 */
@Aspect
@Component
@Order
public class ResultAspect {

    @Autowired
    SupportConstant constant;

    /**
     * 定义切入点,com.wit.sc.*.web.controller包下所有以Controller结尾的类下的所有方法
     */
    @Pointcut("execution(* com.wit.sc.*.web.controller..*Controller.*(..)) && !execution(* com.wit.sc.*.web.*.page.*.*(..))")
    public void pointcut(){}

    /**
     * 能执行到此处，说明程序没有任何异常，如果有异常，会通过全局异常处理，直接返回结果，而不会执行到此处
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object doAfterReturning(ProceedingJoinPoint pjp) throws Throwable {
        if(constant.getMethod().isResultWrapEnable()) {
            Object data = pjp.proceed();
            return ResultDto.success(data);
        }
        return pjp.proceed();
    }
}
