package com.wit.sc.zuul.web.controller;

import com.wit.sc.common.api.feign.UserServiceFeigns;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.zuul.web.controller
 * @Description:
 * @date 2019/8/9 17:02
 */
@RestController
public class ZuulCustomController {

    @Resource
    UserServiceFeigns userServiceFeigns;

    @GetMapping("hello")
    public String hello() {
        return "您好,maple";
    }

    @GetMapping("getAllBooks")
    public String getAllBooks() {
        return userServiceFeigns.getAllBooks();
    }
}
