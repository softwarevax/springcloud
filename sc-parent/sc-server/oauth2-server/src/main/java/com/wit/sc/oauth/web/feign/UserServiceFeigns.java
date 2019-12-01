package com.wit.sc.oauth.web.feign;

import com.wit.sc.oauth.web.entity.ResultDto;
import com.wit.sc.oauth.web.entity.UserDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.api.feign
 * @Description:
 * @date 2019/8/11 11:16
 */
@FeignClient(name = "/outh")
public interface UserServiceFeigns {

    /**
     * 根据用户id查询用户详情
     * @param userId
     * @return
     */
    @GetMapping("/api/user/{userId}")
    ResultDto<UserDetail> getUserInfoById(@PathVariable String userId);

    /**
     * 注册用户
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/api/user/register")
    ResultDto<Integer> register(String username, String password);
}
