package com.wit.sc.gateway.route.loader;

import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.SmartLifecycle;

/**
 * @author twcao
 * @Title: RefreshRoute
 * @ProjectName dp-parent
 * @Description: 刷新缓存接口
 * SmartLifecycle在spring容器的所有bean加载完后执行start方法
 * @date 2019/3/13/013 18:48
 */
public interface RefreshRoute extends RouteDefinitionLocator, SmartLifecycle {
}
