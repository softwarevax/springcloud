package com.wit.sc.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ctw
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
@EnableFeignClients("com.wit.sc.common.api.feign")
public class ScZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScZuulApplication.class, args);
    }

}

