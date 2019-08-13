package com.wit.sc.support.configuration.support;

import lombok.Data;

import java.util.Map;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.configuration.support
 * @Description:
 * @date 2019/8/1 10:32
 */
@Data
public class FilterConstant {

    public static final String COMMA = ",";

    private String filterName;

    private boolean enable = true;

    private Integer order;

    /**
     * 多个之间逗号隔开
     */
    private String pattern;

    /**
     * 其他属性配置
     */
    private Map<String, String> properties;
}
