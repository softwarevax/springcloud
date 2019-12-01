package com.wit.sc.oauth.web.controller.api;

import com.wit.sc.oauth.web.entity.ResultDto;
import com.wit.sc.oauth.web.entity.UserDetail;
import com.wit.sc.oauth.web.service.Oauth2UserDetailsService;
import com.wit.sc.oauth.web.service.UserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.oauth.web.controller
 * @Description:
 * @date 2019/8/2 15:44
 */
@RestController
public class UserController {

    /**
     * 日志
     */
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    Oauth2UserDetailsService userDetailsService;


    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 资源服务器提供的受保护接口，提供用户信息
     * @param principal
     * @return
     */
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        logger.info("principal = [{}]", principal);
        return principal;
    }

    @Autowired
    UserDetailService userDetailService;

    /**
     * 根据用户id查询用户详情
     * 需要登录后才可以请求到结果
     * @return
     */
    @GetMapping("/api/user")
    public ResultDto<UserDetail> getUserInfoById(Principal principal) {
        return ResultDto.successT(userDetailService.getUserInfo(principal.getName()));
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return 返回用户id
     */
    @GetMapping("/api/user/register")
    public ResultDto<Integer> registered(String username, String password) {
        logger.info("registered username = [{}], password = [{}]", username, password);
        if(StringUtils.isAnyBlank(username, password)) {
            logger.error("username or password can't be null");
            // 做了全局异常处理
            throw new RuntimeException("username or password can't be null");
        }
        String encodePassword = "{bcrypt}" + passwordEncoder.encode(password);
        int userId = userDetailsService.insertOauth2User(username, encodePassword);
        logger.info("userId = [{}] password [{}] to [{}]", userId, password, encodePassword);
        return ResultDto.successT(userId);
    }
}
