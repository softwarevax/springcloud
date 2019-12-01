package com.wit.sc.oauth.web.service;

import com.wit.sc.oauth.web.dao.OauthUser2Dao;
import com.wit.sc.oauth.web.entity.Oauth2User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.oauth.web.service
 * @Description:
 * @date 2019/8/3 15:02
 */
@Service
public class Oauth2UserDetailsService implements UserDetailsService, Serializable {

    /**
     * 日志
     */
    public static final Logger logger = LoggerFactory.getLogger(Oauth2UserDetailsService.class);

    @Autowired
    OauthUser2Dao oauth2Dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Oauth2User user = oauth2Dao.getOauth2UserByUsername(username);
        return user;
    }

    /**
     * 新增用户，返回用户id
     * @param username
     * @param password
     * @return
     */
    public int insertOauth2User(@NotNull String username, @NotNull String password) {
        Oauth2User oauth2User = new Oauth2User();
        oauth2User.setUsername(username);
        oauth2User.setPassword(password);
        oauth2User = oauth2Dao.saveAndFlush(oauth2User);
        return oauth2User.getId();
    }
}
