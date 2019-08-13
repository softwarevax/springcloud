package com.wit.sc.config.custom;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.config.custom
 * @Description:
 * @date 2019/8/8 15:03
 */
@Profile("redis")
@Configuration
public class RedisEnvironmentRepository implements EnvironmentRepository,Ordered {

    @Override
    public Environment findOne(String application, String profile, String label) {
        Environment environment = new Environment(application, "default", label, (String)null, (String)null);
        Map<String, String> next = new HashMap<>();
        next.put("custom", "1510");
        environment.add(new PropertySource(application + "-" + "-custom", next));
        return environment;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}