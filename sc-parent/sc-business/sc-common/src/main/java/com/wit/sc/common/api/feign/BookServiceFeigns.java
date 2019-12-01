package com.wit.sc.common.api.feign;

import com.wit.sc.common.api.feign.fallback.BookServiceFeignsFallback;
import com.wit.sc.common.domain.dto.ResultDto;
import com.wit.sc.common.domain.entity.book.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.api.feign
 * @Description:
 * @date 2019/8/5 8:46
 */
@FeignClient(name = "sc-book", fallbackFactory = BookServiceFeignsFallback.class)
public interface BookServiceFeigns {

    /**
     * 获取所有的书
     * @return
     */
    @GetMapping("/api/book/getAllBooks")
    String getAllBooks();

    /**
     * 根据书的id获取书
     * 其中bookId不能为空
     * @return
     */
    @PostMapping("/api/book/getBookById")
    ResultDto<Book> getBookById(@RequestParam("bookId") String bookId);

    @PostMapping("/api/book/buyBooks")
    String buyBooks(@RequestParam("bookId") String bookId, @RequestParam("bookNum") Integer bookNum);
}
