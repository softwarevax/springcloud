package com.wit.sc.portal.config.oauth2;

import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.context.request.RequestContextListener;

import javax.annotation.Resource;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.config.oauth
 * @Description:
 * @date 2019/8/1 20:25
 */
@Configuration
@EnableConfigurationProperties
public class Oauth2ClientConfigure {

    @Resource
    private ClientCredentialsResourceDetails clientCredentialsResourceDetails;

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    @Primary
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext feignOAuth2ClientContext) {
        return new OAuth2FeignRequestInterceptor(feignOAuth2ClientContext, clientCredentialsResourceDetails);
    }

    @Bean
    public OAuth2RestTemplate clientCredentialsRestTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails);
    }
}
