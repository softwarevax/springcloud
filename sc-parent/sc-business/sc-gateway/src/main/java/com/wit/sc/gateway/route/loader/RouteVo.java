package com.wit.sc.gateway.route.loader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.util.*;

/**
 * @author twcao
 * @Title: RouteVo
 * @ProjectName dp-parent
 * @Description: 路由实体
 * @date 2019/3/13/013 20:24
 */
class RouteVo {

    private String id;

    private String uri;

    private String predicates;

    private String filters;

    private Boolean enabled;

    private Integer order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public URI getUri() {
        return URI.create(this.uri);
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<PredicateDefinition> getPredicates() {
        List<PredicateDefinition> predicateDefinitionList = new ArrayList<>();
        List<String> predicatesList = JSON.parseArray(this.predicates, String.class);
        if(CollectionUtils.isEmpty(predicatesList)) {
            return new ArrayList<>();
        }
        predicatesList.forEach(predicateStr -> {
            JSONObject predicateJsonObj = JSON.parseObject(predicateStr);
            Set<String> keys = predicateJsonObj.keySet();
            Iterator<String> keyIterator = keys.iterator();
            for(int i = 0; i < keys.size() && keyIterator.hasNext(); i++) {
                String key = keyIterator.next();
                PredicateDefinition predicateDefinition = new PredicateDefinition();
                predicateDefinition.setName(key);
                String value = predicateJsonObj.getString(key);
                Map<String, String> predicateParams = new HashMap<>(8);
                if(StringUtils.countMatches(value,",") == 1) {
                    //如果只有一个“,”,则自带key值
                    predicateParams.put(StringUtils.split(value, ",")[0], StringUtils.split(value, ",")[1]);
                } else if(StringUtils.countMatches(value,",") == 0){
                    //如果没有“,”,则key = _genkey_i
                    predicateParams.put("_genkey_0", value);
                } else {
                }
                predicateDefinition.setArgs(predicateParams);
                predicateDefinitionList.add(predicateDefinition);
            }
        });
        return predicateDefinitionList;
    }

    public void setPredicates(String predicates) {
        this.predicates = predicates;
    }

    public List<FilterDefinition> getFilters() {
        List<FilterDefinition> filterDefinitionList = new ArrayList<>();
        List<String> filtersList = JSON.parseArray(this.filters, String.class);
        if(CollectionUtils.isEmpty(filtersList)) {
            return new ArrayList<>();
        }
        filtersList.forEach(filterStr -> {
            JSONObject filterJsonObj = JSON.parseObject(filterStr);
            Set<String> keys = filterJsonObj.keySet();
            Iterator<String> keyIterator = keys.iterator();
            for(int i = 0; i < keys.size() && keyIterator.hasNext(); i++) {
                String key = keyIterator.next();
                FilterDefinition filterDefinition = new FilterDefinition();
                filterDefinition.setName(key);
                String value = filterJsonObj.getString(key);
                Map<String, String> filterParams = new HashMap<>(8);
                if(StringUtils.countMatches(value,",") == 1) {
                    //如果只有一个“,”,则自带key值
                    filterParams.put("_genkey_0", StringUtils.split(value, ",")[0]);
                    filterParams.put("_genkey_1", StringUtils.split(value, ",")[1]);
                } else if(StringUtils.countMatches(value,",") == 0){
                    //如果没有“,”,则key = _genkey_i
                    filterParams.put("_genkey_0", value);
                } else {
                    //不合法的参数设置
                }
                filterDefinition.setArgs(filterParams);
                filterDefinitionList.add(filterDefinition);
            }
        });
        return filterDefinitionList;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
