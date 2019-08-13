package com.wit.sc.support.web.service.impl;

import com.wit.sc.support.web.domain.RequestToMethodItem;
import com.wit.sc.support.web.service.InterfaceHandler;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.web.service.impl
 * @Description:
 * @date 2019/8/5 9:45
 */
@Service
public class InterfaceHandlerImpl implements InterfaceHandler {

    @Override
    public List<RequestToMethodItem> getAllRequestToMethodItems(ServletContext sc) {
        Assert.notNull(sc, "ServletContext can't be null");
        WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(sc);

        //请求url和处理方法的映射
        List<RequestToMethodItem> requestToMethodItemList = new ArrayList<>();
        //获取所有的RequestMapping
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext,
                HandlerMapping.class, true, false);

        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
            //只需要RequestMappingHandlerMapping中的URL映射
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
                    RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
                    HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();

                    RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
                    String requestType = methodCondition.getMethods().stream().map(vo -> String.valueOf(vo)).collect(Collectors.joining(","));

                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                    //  String requestUrl = SetUtils.first(patternsCondition.getPatterns());
                    String requestUrl = patternsCondition.getPatterns().stream().map(vo -> String.valueOf(vo)).collect(Collectors.joining(","));;
                    String controllerName = mappingInfoValue.getBeanType().toString();
                    String requestMethodName = mappingInfoValue.getMethod().getName();
                    Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
                    RequestToMethodItem item = new RequestToMethodItem(requestUrl, requestType, controllerName, requestMethodName, methodParamTypes);

                    requestToMethodItemList.add(item);
                }
            }
        }
        return requestToMethodItemList;
    }

    @Override
    public List<String> getAllInterfaceUrls(ServletContext sc) {
        List<RequestToMethodItem> interfaces = getAllRequestToMethodItems(sc);
        List<String> interfaceUrls =  interfaces.stream().map(RequestToMethodItem::getRequestUrl).collect(Collectors.toList());
        return interfaceUrls;
    }

    @Override
    public boolean isInterface(ServletContext sc, String methodName) {
        List<RequestToMethodItem> interfaces = getAllRequestToMethodItems(sc);
        List<String> interfaceMethodNames =  interfaces.stream().map(RequestToMethodItem::getRequestMethodName).collect(Collectors.toList());
        if(interfaceMethodNames.contains(methodName)) {
            return true;
        }
        return false;
    }
}
