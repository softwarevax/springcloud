package com.wit.sc.support.configuration.plugins.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.configuration.plugins.filter
 * @Description:
 * @date 2019/6/13 23:12
 */
public class PioneerFiltertWrapper extends HttpServletRequestWrapper {

    private Map<String, String> headers;

    public PioneerFiltertWrapper(HttpServletRequest request) {
        super(request);
        headers = new HashMap<>();
    }

    @Override
    public String getHeader(String name) {
        String headerValue = headers.get(name);
        if (StringUtils.isNotBlank(headerValue)){
            return headerValue;
        }
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> set = new HashSet<>(headers.keySet());
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            set.add(e.nextElement());
        }
        return Collections.enumeration(set);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }
}
