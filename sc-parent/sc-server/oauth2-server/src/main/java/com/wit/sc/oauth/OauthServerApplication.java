package com.wit.sc.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author ctw
 * 获取token:
 * http://localhost:9030/oauth/token?grant_type=password&username=user_1&password=123456&scope=server&client_id=client_2&client_secret=123456
 */
@SpringBootApplication
@EnableResourceServer
public class OauthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthServerApplication.class, args);
	}
}
