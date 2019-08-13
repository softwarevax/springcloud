package com.wit.sc.registry.listener;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ctw
 * @Title: EurekaStateListener
 * @ProjectName sc-parent
 * @Description: 服务注册和下线监听事件
 * @date 2019/1/18/01812:50
 */
@Component
public class EurekaStateListener {

    private final static Logger logger = LoggerFactory.getLogger(EurekaStateListener.class);

    @EventListener
    public void listen(EurekaInstanceCanceledEvent  event) {
        logger.info("服务{}已下线", event.getAppName());
        logger.info("server地址信息{}", event.getServerId());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info("服务{}进行注册", instanceInfo.getAppName()+ instanceInfo.getHostName() +"  "+ instanceInfo.getIPAddr() +"  "+ instanceInfo.getPort());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        logger.info("服务{}进行续约", event.getServerId() +"  "+ event.getAppName());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info("注册中心启动,{}", System.currentTimeMillis());
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info("注册中心服务端启动,{}", System.currentTimeMillis());
    }
}