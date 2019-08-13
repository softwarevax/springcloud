package com.wit.sc.book;

import com.wit.sc.support.configuration.annotation.EnableSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableSupport
@EnableEurekaClient
@EnableTransactionManagement
@EnableFeignClients("com.wit.sc.common.api.feign")
@MapperScan(value = "com.wit.sc.book.web.dao")
@SpringBootApplication(scanBasePackages = "com.wit.sc")
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

}
