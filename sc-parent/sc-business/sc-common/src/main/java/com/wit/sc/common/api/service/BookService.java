package com.wit.sc.common.api.service;

import com.wit.sc.common.domain.entity.book.Book;

import java.util.List;

/**
 * @author ctw
 * @Title: BookService
 * @ProjectName sc-parent
 * @Description: 书-service
 * @date 2019/1/6/00617:02
 */
public interface BookService {

    /**
     * 获取所有的书
     * @return
     */
    List<Book> getAll();

    /**
     * 根据书的id获取书
     * 其中bookId不能为空
     * @param bookId 书的id
     * @return 如果结果不符合要求，则返回空实体
     */
    Book getBookById(String bookId);
}
