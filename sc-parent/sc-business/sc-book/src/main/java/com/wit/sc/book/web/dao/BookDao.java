package com.wit.sc.book.web.dao;

import com.wit.sc.common.domain.entity.book.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ctw
 * @Title: BookDao
 * @ProjectName sc-parent
 * @Description: 书数据访问层-db
 * @date 2019/1/6/00616:05
 */
@Mapper
public interface BookDao {

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
    List<Book> getBookById(String bookId);
}
