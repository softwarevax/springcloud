package com.wit.sc.book.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.config.oauth
 * @Description:
 * @date 2019/8/3 13:15
 * authorization_code,client_credentials,password,implicit,refresh_token
 *
 * @EnableResourceServer 该注解是关键，否则上层服务携带认证过的信息请求当前服务时，会被拦截到登陆页面
 *
 * ResourceServerConfigurerAdapter(order=3) 和 WebSecurityConfigurerAdapter(order=100)
 *故相同的设置，前者的生效
 *
 * 去掉@EnableResourceServer注解，未授权页面会被重定向到登陆页
 */
@EnableOAuth2Sso
@Configuration
@EnableOAuth2Client
@EnableResourceServer
public class OauthClientSecurityConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    AuthExceptionEntryPoint authExceptionEntryPoint;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 自定义求授权的异常处理
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(authExceptionEntryPoint).accessDeniedHandler(customAccessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers( "/login**")
                .permitAll()
                .anyRequest()
                .authenticated().and().httpBasic();
    }
}
