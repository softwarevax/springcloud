package com.wit.sc.sleuthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author twcao
 */
@EnableEurekaClient
@EnableZipkinServer
@SpringBootApplication
public class SleuthServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SleuthServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SleuthServerApplication.class, args);
    }
}
