package com.wit.sc.support.configuration.plugins.aspect;

import com.wit.sc.support.web.domain.MethodRecordBo;
import com.wit.sc.support.web.service.MethodRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.service.impl
 * @Description:
 * @date 2019/6/13 22:00
 */
@Component
public class MethodAspectHandler {

    private Logger logger = LoggerFactory.getLogger(MethodAspectHandler.class);

    @Autowired
    MethodRecordService methodRecordService;

    /**
     * 避免一次接口查询等待时间过长
     * @param methodRecordVo
     */
    @Async
    public void methodHandle(MethodRecordBo methodRecordVo) {
        logger.info("MethodAspectHandler.methodHandle methodRecordVo = {}", methodRecordVo);
        methodRecordService.insert(methodRecordVo);
    }
}
