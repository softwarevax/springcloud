package com.wit.sc.oauth.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.oauth.web.controller
 * @Description:
 * @date 2019/8/3 13:12
 */
@Controller
public class LoginController {
    /**
     * 自定义登录页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
