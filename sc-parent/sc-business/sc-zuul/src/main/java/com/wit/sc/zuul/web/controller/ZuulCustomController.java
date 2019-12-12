package com.wit.sc.zuul.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.zuul.web.controller
 * @Description:
 * @date 2019/8/9 17:02
 */
@RestController
public class ZuulCustomController {

    @GetMapping("hello")
    public String hello() {
        return "您好,maple";
    }
}
