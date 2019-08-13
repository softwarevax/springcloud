package com.wit.sc.book.web.service;

import com.wit.sc.book.web.dao.BookDao;
import com.wit.sc.common.api.service.BookService;
import com.wit.sc.common.domain.entity.book.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ctw
 * @Title: BookServiceImpl
 * @ProjectName sc-parent
 * @Description: 书的接口实现-impl
 * @date 2019/1/6/00617:03
 */
@Service
public class BookServiceImpl implements BookService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    BookDao bookDao;

    /**
     * 获取所有的书
     * @return
     */
    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    /**
     * 根据书的id获取书
     * 其中bookId不能为空
     * @param bookId 书的id
     * @return 如果结果不符合要求，则返回空实体
     */
    @Override
    public Book getBookById(String bookId) {
        Book book = null;
        List<Book> bookList = bookDao.getBookById(bookId);
        if(CollectionUtils.isEmpty(bookList) || bookList.size() > 1) {
            //未查询到预期结果
            logger.error("根据bookId查询单个书实体: {}", bookList);
            return null;
        }
        book = bookList.get(0);
        return book;
    }
}
