package com.wit.sc.zuul.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wit.sc.common.utils.CommonUtils;
import com.wit.sc.zuul.config.feign.FeignHystrixConcurrencyStrategy;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.zuul.config
 * @Description:
 * @date 2019/8/9 16:58
 */
@Component
public class ComponentRegister {

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
