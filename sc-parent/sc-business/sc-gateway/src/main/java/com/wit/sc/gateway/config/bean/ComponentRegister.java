package com.wit.sc.gateway.config.bean;

import com.wit.sc.gateway.route.loader.RefreshCacheRouteAtFix;
import com.wit.sc.gateway.route.white.CompositeWhiteLoader;
import com.wit.sc.gateway.route.white.JdbcWhiteUrlLoader;
import com.wit.sc.gateway.route.white.PropertiesWhiteUrlLoader;
import com.wit.sc.gateway.route.white.WhiteUrlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author twcao
 * @Title: ConmonentRegister
 * @ProjectName dp-parent
 * @Description: bean注册
 * @date 2019/3/13/013 18:11
 */
@Configuration
public class ComponentRegister {

    private Logger logger = LoggerFactory.getLogger(Configuration.class);

    /**
     * cors相关默认属性
     */
    private static final String ALLOWED_HEADERS = "*";
    private static final String ALLOWED_METHODS = "*";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String ALLOWED_Expose = "*";
    private static final String MAX_AGE = "18000L";

    /**
     * 注册定时刷新路由的bean
     * @param jdbcTemplate
     * @return
     */
    @Bean
    RefreshCacheRouteAtFix refreshCacheRouteAtFix(JdbcTemplate jdbcTemplate) {
        return new RefreshCacheRouteAtFix(jdbcTemplate);
    }

    /**
     * 加载jdbc白名单
     * @param dataSource 数据源
     * @return
     */
    @Bean
    WhiteUrlLoader jdbcWhiteUrlLoader(DataSource dataSource) {
        return new JdbcWhiteUrlLoader(dataSource);
    }

    /**
     * 加载属性配置文件白名单
     * @return
     */
    @Bean
    WhiteUrlLoader propertiesWhiteUrlLoader() {
        return new PropertiesWhiteUrlLoader();
    }


    /**
     * 白名单属性加载器
     * @param whiteUrlLoaders
     * @return
     */
    @Bean
    @Primary
    WhiteUrlLoader whiteUrlLoader(List<WhiteUrlLoader> whiteUrlLoaders) {
        return new CompositeWhiteLoader(whiteUrlLoaders);
    }

    /**
     * 跨域设置
     * @return
     */
    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                headers.add("Access-Control-Max-Age", MAX_AGE);
                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                headers.add("Access-Control-Expose-Headers", ALLOWED_Expose);
                headers.add("Access-Control-Allow-Credentials", "true");
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }

    /**
     * 向spring容器动态注册bean，bean依赖的值会被注册，依赖bean的对象中的bean值也会被注入
     *
     * 如:
     * BeanDefinition beanDe = BeanDefinitionBuilder.rootBeanDefinition(RefreshCacheRouteAtFix.class).getBeanDefinition();
     * registry.registerBeanDefinition("refreshCacheRouteAtFix", beanDe);
     * 1、RefreshCacheRouteAtFix中的jdbcTemplate会被注入
     * 2、GatewayAutoConfiguration中的方法routeDefinitionLocator要求注入所有实现了RouteDefinitionLocator接口的类，
     *    refreshCacheRouteAtFix也会被注入方法的list对象中
     *
     *
     * 用途: eg:可动态添加路由拦截规则 ---> 只要将实现了AbstractRoutePredicateFactory接口的类，
     *                                  动态注入spring容器中，即可用于路由拦截，路由拦截规则可在数据库中直接配置，
     *                                  如:PathRoutePredicateFactory
     * @param env
     * @return
     */
    @Bean
    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor(Environment env){
        return new BeanDefinitionRegistryPostProcessor() {

            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
                /*BeanDefinition beanDe = BeanDefinitionBuilder.rootBeanDefinition(RefreshCacheRouteAtFix.class).getBeanDefinition();
                registry.registerBeanDefinition("refreshCacheRouteAtFix", beanDe);*/
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

            }
        };
    }
}
