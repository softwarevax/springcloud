package com.wit.sc.portal.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.controller
 * @Description: sc-portal门户页面跳转
 * @date 2019/8/3 10:01
 */
@Controller
public class PageViewController {

    /**
     * 主页
     * @return
     */
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}
