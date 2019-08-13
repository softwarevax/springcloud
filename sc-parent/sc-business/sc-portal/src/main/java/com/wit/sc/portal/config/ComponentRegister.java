package com.wit.sc.portal.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wit.sc.common.utils.CommonUtils;
import com.wit.sc.portal.config.feign.FeignHystrixConcurrencyStrategy;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.config
 * @Description: bean注册
 * @date 2019/6/13 22:55
 */
@Configuration
public class ComponentRegister implements WebMvcConfigurer {

    /**
     * 传递请求头head
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return CommonUtils::writeRestTemplateHeader;
    }

    /**
     * 使传递请求头生效，配置自定义隔离效果。
     * 如果隔离策略不配置，但配置了熔断器，请求头将无法传递
     * @return
     */
    @Bean
    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
        return new FeignHystrixConcurrencyStrategy();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 解决乱么提供bean
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}

/**
 @Bean
 public ServletRegistrationBean druidServlet() {
 ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
 // IP白名单
 servletRegistrationBean.addInitParameter("allow", "*");
 // IP黑名单(共同存在时，deny优先于allow)
 servletRegistrationBean.addInitParameter("deny", "*");
 //控制台管理用户
 servletRegistrationBean.addInitParameter("loginUsername", "admin");
 servletRegistrationBean.addInitParameter("loginPassword", "1111");
 //是否能够重置数据 禁用HTML页面上的“Reset All”功能
 servletRegistrationBean.addInitParameter("resetEnable", "false");
 return servletRegistrationBean;
 }

 @Bean
 public FilterRegistrationBean filterRegistrationBean() {
 FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
 filterRegistrationBean.addUrlPatterns("/*");
 filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
 return filterRegistrationBean;
 }

 **/