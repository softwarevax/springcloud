package com.wit.sc.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.config
 * @Description:
 * @date 2019/8/3 23:47
 */
@Component
@EnableWebMvc
@ComponentScan
public class WebMvcConfigure implements WebMvcConfigurer {

    @Autowired
    HttpMessageConverter responseBodyConverter;

    @Autowired
    MappingJackson2HttpMessageConverter messageConverter;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //解决中文乱码
        converters.add(responseBodyConverter);
        //解决 添加解决中文乱码后 上述配置之后，返回json数据直接报错 500：no convertter for return value of type
        converters.add(messageConverter);
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/home");
        registry.addViewController("/home");
    }

    /**
     * 将访问路径以static开头的资源映射到public路径下
     * @param registry
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/public/");
    }
}
