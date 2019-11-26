package com.wit.sc.portal.config.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.config.oauth
 * @Description:
 * @date 2019/8/3 13:15
 *
 * @EnableResourceServer 该注解是关键，否则上层服务携带认证过的信息请求当前服务时，会被拦截到登陆页面
 *  *
 *  * ResourceServerConfigurerAdapter(order=3) 和 WebSecurityConfigurerAdapter(order=100)
 *  *故相同的设置，前者的生效
 *  *
 *  * 去掉@EnableResourceServer注解，未授权页面会被重定向到登陆页
 */
@EnableOAuth2Sso
@Configuration
@EnableOAuth2Client
@EnableResourceServer
public class OauthClientSecurityConfigure extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //决定哪些请求需要认证，这里设置为所有请求
                .antMatcher("/**")
                .authorizeRequests()
                //不拦截路径表达式
                .antMatchers( "/login**")
                .permitAll()
                .anyRequest()
                .authenticated().and().httpBasic();
    }
}
