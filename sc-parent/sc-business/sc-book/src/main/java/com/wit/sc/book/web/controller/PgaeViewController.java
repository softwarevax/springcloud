package com.wit.sc.book.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.controller
 * @Description:
 * @date 2019/8/3 10:01
 */
@Controller
public class PgaeViewController {

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }
}
