package com.wit.sc.book.web.controller;

import com.wit.sc.common.api.service.BookService;
import com.wit.sc.common.domain.dto.ResultDto;
import com.wit.sc.common.domain.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author ctw
 * @Title: BookController
 * @ProjectName sc-parent
 * @Description: 书-controller
 * @date 2019/1/6/00617:10
 */
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    /**
     * 获取所有的书
     * @return
     */
    @GetMapping("getAllBooks")
    public String getAllBooks(HttpSession session) {
        String bookSessionId = session.getId();
        String portalSerssionId = (String) session.getAttribute("sessionId");
        System.out.println("portal : book ====> " + portalSerssionId + " : " + bookSessionId);
        return ResultDto.success(bookService.getAll());
    }

    /**
     * 根据书的id获取书
     * 其中bookId不能为空
     * @param bookId 书的id
     * @return 如果结果不符合要求，则返回空实体
     */
    @PostMapping("getBookById")
    public ResultDto<Book> getBookById(@RequestParam String bookId) {
        return ResultDto.successT(bookService.getBookById(bookId));
    }
}
