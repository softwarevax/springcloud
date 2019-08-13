package com.wit.sc.support.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.web.domain
 * @Description:
 * @date 2019/8/1 16:39
 */
@Data
@AllArgsConstructor
public class RequestToMethodItem {

    private String requestUrl;

    private String requestType;

    private String controllerName;

    private String requestMethodName;

    private Class<?>[] methodParamTypes;
}
