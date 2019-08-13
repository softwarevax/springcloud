package com.wit.sc.support.configuration;

import com.wit.sc.support.configuration.plugins.filter.PioneerFilter;
import com.wit.sc.support.configuration.support.FilterConstant;
import com.wit.sc.support.configuration.support.SupportConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextListener;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ctw
 * @Title: SupportAutoConfiguration
 * @ProjectName support-spring-boot-starter
 * @Description: support自动配置类
 * 使com.wit.sc.support包下的web组件可以注册到spring容器中
 *  * 引入SupportConstant常量
 * @date 2018/12/6/006 12:46
 */
@ComponentScan(basePackages = {"com.wit.sc.support"})
@EnableConfigurationProperties(value = SupportConstant.class)
public class SupportAutoConfiguration {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SupportConstant supportConstant;

    @Bean
    public SupportCommandLineRunner supportCommandLineRunner() {
        return new SupportCommandLineRunner(this.supportConstant);
    }

    @Bean
    public RequestContextListener requestContextListenerBean() {
        return new RequestContextListener();
    }

    /**
     * 从filters变量中获取的过滤器名称为pioneerFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean pioneerFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new PioneerFilter());
        FilterConstant pioneerFilterConstant = new FilterConstant();
        if(!CollectionUtils.isEmpty(supportConstant.getFilters())) {
            pioneerFilterConstant = supportConstant.getFilters().get(PioneerFilter.FILTE_RNAME);
        }
        registration.setEnabled(pioneerFilterConstant.isEnable());
        if(!StringUtils.isEmpty(pioneerFilterConstant.getFilterName())) {
            //设置过滤器名称
            registration.setName(pioneerFilterConstant.getFilterName());
        }
        if(!StringUtils.isEmpty(pioneerFilterConstant.getPattern())) {
            //设置过滤器拦截路径
            List<String> urlPattern = Arrays.asList(StringUtils.split(pioneerFilterConstant.getPattern(), FilterConstant.COMMA));
            registration.setUrlPatterns(urlPattern);
        }
        if(pioneerFilterConstant.getOrder() != null) {
            registration.setOrder(pioneerFilterConstant.getOrder());
        }
        Map<String, String> properties = pioneerFilterConstant.getProperties();
        if(!CollectionUtils.isEmpty(properties)) {
            registration.setInitParameters(properties);
        }
        return registration;
    }
}