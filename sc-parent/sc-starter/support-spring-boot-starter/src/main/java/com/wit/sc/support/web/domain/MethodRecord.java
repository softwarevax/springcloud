package com.wit.sc.support.web.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.entity
 * @Description:
 * @date 2019/6/13 23:47
 */
@Data
public class MethodRecord implements Serializable {

    /**
     * 方法id
     */
    private String methodId;

    /**
     * 线程名称
     */
    private String threadName;

    /**
     * 进入方式的时间
     */
    private Long startTime;

    /**
     * 方法执行结束的时间
     */
    private Long endTime;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法的修饰词
     */
    private Integer modifier;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法参数
     */
    private String argId;

    /**
     * 方法的返回结果
     */
    private String resultId;


    //以下属性均可能为空,web请求时，才有值
    /**
     * 请求的id
     */
    private String trackId;

    /**
     * User-Agent
     */
    private String userAgent;

    /**
     * 服务器ip和端口
     */
    private String serverHost;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求的类型，GET,POST
     */
    private String methodType;

    /**
     * 客户端ip
     */
    private String clientHost;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
