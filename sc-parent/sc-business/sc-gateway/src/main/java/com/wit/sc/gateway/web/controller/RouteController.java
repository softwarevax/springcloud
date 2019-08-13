package com.wit.sc.gateway.web.controller;

import com.wit.sc.gateway.route.loader.Route;
import com.wit.sc.gateway.route.loader.RoutePredicate;
import com.wit.sc.gateway.web.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 单个节点新增路由信息，存在数据同步问题，故路由信息均需要从数据库获取，才能保证数据一致性(或使用redis存储)
 * 通过接口添加的会检查数据的合法性
 *
 * 只操作缓存(InMemoryRouteDefinitionRepository)中的路由信息，不修改数据库中的数据
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    /**
     * 增加路由
     * @param gateway
     * @return
     * 如:
        {
            "id": "kylin",
            "predicates": [{
                "name": "Path",
                "args": {
                    "pattern": "/kylin/**"
                }
            }],
            "filters": [],
            "uri": "http://172.16.16.67:7070",
            "order": 0
        }
     */
    @PostMapping("/add")
    public String add(@RequestBody Route gateway) {
        try {
            RouteDefinition definition = assembleRouteDefinition(gateway);
            return this.dynamicRouteService.add(definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "succss";
    }

    /**
     * 删除路由
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id);
    }

    /**
     * 更新路由
     * @param gateway
     * @return
     */
    @PostMapping("/update")
    public String update(@RequestBody Route gateway) {
        RouteDefinition definition = assembleRouteDefinition(gateway);
        return this.dynamicRouteService.update(definition);
    }

    /**
     * 验证路由合法性
     * @param gateway
     * @return
     */
    private RouteDefinition assembleRouteDefinition(Route gateway) {
        RouteDefinition definition = new RouteDefinition();
        List<PredicateDefinition> pdList=new ArrayList<>();
        definition.setId(gateway.getId());
        List<RoutePredicate> gatewayPredicateDefinitionList=gateway.getPredicates();
        for (RoutePredicate gpDefinition: gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);
        URI uri = UriComponentsBuilder.fromHttpUrl(gateway.getUri()).build().toUri();
        definition.setUri(uri);
        return definition;
    }

}
