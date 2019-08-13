package com.wit.sc.support.configuration;

import com.alibaba.fastjson.JSON;
import com.wit.sc.support.configuration.support.SupportConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * @author twcao
 * @Title: SupportCommandLineRunner
 * @ProjectName support-spring-boot-starter
 * @Description: 项目启动时运行的操作
 * @date 2018/12/6/006 14:30
 */
public class SupportCommandLineRunner implements CommandLineRunner {

    protected final Logger logger = LoggerFactory.getLogger(SupportCommandLineRunner.class);

    private SupportConstant supportConstant;

    public SupportCommandLineRunner(SupportConstant uaacConstant) {
        this.supportConstant = uaacConstant;
    }

    @Override
    public void run(String... strings) {
        logger.info("support constant value: {}",JSON.toJSONString(supportConstant));
    }
}