package com.wit.sc.gateway.route.loader;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author twcao
 * @Title: RefreshCacheRouteAtFix
 * @ProjectName dp-parent
 * @Description: 定时刷新缓存的路由
 * @date 2019/3/13/013 18:48
 */
public class RefreshCacheRouteAtFix implements RefreshRoute {

    /**
     * 路由表的名称
     * filters和predicates的名称可以自定义
     * filters： RequestRateLimiter（redis-rate-limiter.replenishRate、redis-rate-limiter.burstCapacity）可限流
     * predicates After、Before、Between、Cookie、Header、Host、Method、Path、Query、RemoteAddr
     * After: 2018-12-25T14:33:47.789+08:00                                         只有在该时间之后请求，才会被转发
     * Before: 2018-12-25T14:33:47.789+08:00                                        只有在该时间之前请求，才会被转发
     * Between: 2018-12-25T14:33:47.789+08:00, 2018-12-26T14:33:47.789+08:00        只有在该时间段之间请求，才会被转发
     * Cookie: cookiename, cookievalue                                              只有cookie中含有key = cookiename, value = cookievalue,才会被转发
     * Header： X-Request-Id, \d+                                                   只有请求头含有X-Request-Id，且值为数字的才会被转发
     * Host： **.somehost.org,**.anotherhost.org                                    只有请求host符合匹配规则(Ant style)的，才会被转发
     * Method： GET                                                                 只有请求方法为GET，才会被转发
     * Path： /kylin/**                                                             只有请求路径符合表达式，才会被转发
     * Query： baz                                                                  只有请求路参数含有baz的，才会被转发
     * Query： baz，bz.                                                             只有请求路参数含有baz的，且值符合"bz."表达式的才会被转发,如baz,bas等
     * RemoteAddr： 10.4.127.1/24                                                   只有请求地址符合表达式的，才会被转发,如10.4.127.133等
     */

    /**
     * sql查询语句
     */
    public static final String ROUTE_SQL = "select * from gateway_definition where enabled = true";

    /**
     * 路由缓存,一个线程读，一个线程写
     */
    private List<RouteDefinition> routes = new ArrayList<>();

    /**
     * 刷新缓存任务的执行周期
     */
    private int taskPeriod = 5;

    private boolean flag = false;

    /**
     * jdbc模板
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * 构造函数私有化，因为jdbcTemplate必须设置
     */
    private RefreshCacheRouteAtFix() {}

    public RefreshCacheRouteAtFix(JdbcTemplate jdbcTemplate) {
        Assert.notNull(jdbcTemplate, "jdbcTemplate can't be null");
         this.jdbcTemplate = jdbcTemplate;
     }

   /**
     * 每次路由请求，均会调用所有实现了RouteDefinitionLocator接口的getRouteDefinitions方法
     * 直接取出缓存的数据，而缓存的从数据库定期获取
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routes);
    }

    @Override
    public void start() {
        this.flag = true;
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            List<RouteVo> routeVoList = jdbcTemplate.query(ROUTE_SQL, new BeanPropertyRowMapper<>(RouteVo.class));
            if(CollectionUtils.isEmpty(routeVoList)) {
                return;
            }
            List<RouteDefinition> routes = new ArrayList<>();
            routeVoList.forEach(route -> {
                RouteDefinition routeDefinition = new RouteDefinition();
                routeDefinition.setId(route.getId());
                routeDefinition.setFilters(route.getFilters());
                routeDefinition.setOrder(route.getOrder());
                routeDefinition.setPredicates(route.getPredicates());
                routeDefinition.setUri(route.getUri());
                routes.add(routeDefinition);
            });
            this.routes.clear();
            this.routes = routes;
        }, 0, taskPeriod, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        this.flag = false;
        this.routes = null;
    }

    @Override
    public boolean isRunning() {
        return this.flag;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        runnable.run();
    }

    @Override
    public int getPhase() {
        return 0;
    }
}

