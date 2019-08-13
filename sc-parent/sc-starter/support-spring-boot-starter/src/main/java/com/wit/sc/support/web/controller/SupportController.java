package com.wit.sc.support.web.controller;

import com.wit.sc.support.web.service.InterfaceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.web.controller
 * @Description:
 * @date 2019/8/1 16:43
 */
@RestController
public class SupportController {

    @Autowired
    InterfaceHandler interfaceHandler;

    /**
     * 返回所有接口
     * @param request
     * @return
     */
    @GetMapping("interfaces")
    public Object interfaces(HttpServletRequest request) {
        ServletContext servletContext = request.getSession().getServletContext();
        return interfaceHandler.getAllRequestToMethodItems(servletContext);
    }

    /**
     * 返回所有接口路径
     * @param request
     * @return
     */
    @GetMapping("interfaceUrls")
    public Object interfaceUrls(HttpServletRequest request) {
        ServletContext servletContext = request.getSession().getServletContext();
        return interfaceHandler.getAllInterfaceUrls(servletContext);
    }
}
