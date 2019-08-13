package com.wit.sc.common.api.feign.fallback;

import com.wit.sc.common.api.feign.BookServiceFeigns;
import com.wit.sc.common.domain.dto.ResultDto;
import com.wit.sc.common.domain.entity.book.Book;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.api.feign.fallback
 * @Description:
 * @date 2019/8/5 21:24
 */
@Component
public class BookServiceFeignsFallback implements FallbackFactory<BookServiceFeigns> {

    public static final Logger logger = LoggerFactory.getLogger(BookServiceFeignsFallback.class);

    @Override
    public BookServiceFeigns create(Throwable throwable) {
        logger.error(throwable.getMessage());
        return new BookServiceFeigns() {

            @Override
            public String getAllBooks() {
                Book book = new Book();
                book.setBookId("-1");
                List<Book> books = Arrays.asList(book);
                return ResultDto.fail(books);
            }

            @Override
            public ResultDto<Book> getBookById(String bookId) {
                Book book = new Book();
                book.setBookId("-1");
                return ResultDto.failT(book);
            }
        };
    }
}
