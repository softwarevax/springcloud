package com.wit.sc.common.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.api.feign
 * @Description:
 * @date 2019/8/11 11:16
 */
@FeignClient(name = "sc-portal")
public interface UserServiceFeigns {

    /**
     * 获取所有的书
     * @return
     */
    @GetMapping("getAllBooks")
    String getAllBooks();
}
