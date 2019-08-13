package com.wit.sc.common.utils;

import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.utils
 * @Description: 公共工具类
 * @date 2019/6/15 11:47
 */
public class CommonUtils {

    public static String SPLIT_IN_LINE = "-";

    public static String uuid(String split) {
        return UUID.randomUUID().toString().replace("-", split);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace(SPLIT_IN_LINE,  "");
    }

    /**
     * 将当前请求头的内容写到 Feign RestTemplate 请求模板中
     * @param requestTemplate
     */
    public static void writeRestTemplateHeader(RequestTemplate requestTemplate) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest request = attrs.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String value = request.getHeader(name);
                    requestTemplate.header(name, value);
                }
            }
        }
    }
}
