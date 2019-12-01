package com.wit.sc.portal.web.controller.api;

import com.wit.sc.common.api.feign.BookServiceFeigns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.controller
 * @Description:
 * @date 2019/6/11 23:58
 */
@RestController
public class UserController {

    public static final String ACTION_KEY = "/api/user";

    public final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    BookServiceFeigns bookServiceFeigns;
}
