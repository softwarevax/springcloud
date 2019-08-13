package com.wit.sc.gateway.route.white;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author twcao
 * @Title: PropertiesWhiteUrlLoader
 * @ProjectName dp-parent
 * @Description: 属性配置文件加载白名单，待扩展
 * @date 2019/3/13/013 15:04
 */
public class PropertiesWhiteUrlLoader implements WhiteUrlLoader {

    /**
     * 日志
     */
    public static final Logger logger = LoggerFactory.getLogger(PropertiesWhiteUrlLoader.class);

    /**
     * 白名单文件路径
     */
    public static String DEFAULT_LOADER_WHITE_PATH = "classpath:/white-urls.xml";

    @Override
    public List<String> loadWhiteUrl() {
        //从white-urls.xml文件加载白名单,具体加载过程略
        List<String> properties = new ArrayList<>();
        properties.add("127.0.0.1");
        return properties;
    }
}
