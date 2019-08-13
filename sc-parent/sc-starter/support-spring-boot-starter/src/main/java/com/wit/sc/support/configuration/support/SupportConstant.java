package com.wit.sc.support.configuration.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author twcao
 * @Title: SupportConstant
 * @ProjectName support
 * @Description: support常量设置
 * @date 2018/10/22 0022 18:32
 */
@Data
@ConfigurationProperties(prefix = "support")
public class SupportConstant {
    private Method method = new Method();

    /**
     * filter
     * PioneerFilter -> pioneerFilter
     * support.filters[pioneerFilter].filterName=pioneerFilter
     * support.filters[pioneerFilter].enable=false
     * support.filters[pioneerFilter].properties[pageSize]=51
     * support.filters[pioneerFilter].properties[whiteUrls]=127.0.0.1,192.168.1.1
     */
    private Map<String, FilterConstant> filters = new HashMap<>();
}