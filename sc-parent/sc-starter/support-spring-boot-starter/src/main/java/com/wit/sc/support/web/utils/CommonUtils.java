package com.wit.sc.support.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.utils
 * @Description: 公共工具类
 * @date 2019/6/15 11:47
 */
public class CommonUtils {

    /**
     * 日志
     */
    public static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static String SPLIT_IN_LINE = "-";

    public static String uuid(String split) {
        return UUID.randomUUID().toString().replace("-", split);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace(SPLIT_IN_LINE,  "");
    }
}
