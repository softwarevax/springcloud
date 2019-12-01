package com.wit.sc.portal.web.controller.api;

import com.wit.sc.common.api.feign.BookServiceFeigns;
import com.wit.sc.portal.config.oauth2.UserAuthenticationManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ctw
 * @Project： springcloud
 * @Package: com.wit.sc.portal.web.controller.api
 * @Description:
 * @date 2019/12/1 15:35
 */
@RestController
public class BookController {

    public static final String ACTION_KEY = "/api/book";

    public final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Resource
    BookServiceFeigns bookServiceFeigns;

    @Autowired
    UserAuthenticationManage authenticationManage;

    @GetMapping(ACTION_KEY + "/buy")
    public String buyBook(HttpServletRequest request, String bookId, int num) {
        return bookServiceFeigns.buyBooks(bookId, num);
    }
}
