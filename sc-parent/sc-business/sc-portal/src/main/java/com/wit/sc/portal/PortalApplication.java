package com.wit.sc.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ctw
 */
@EnableAsync
@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.wit.sc")
@EnableTransactionManagement
@MapperScan("com.wit.sc.portal.web.mapper")
@EnableEurekaClient
@EnableFeignClients({"com.wit.sc.common.api.feign"})
public class PortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}

