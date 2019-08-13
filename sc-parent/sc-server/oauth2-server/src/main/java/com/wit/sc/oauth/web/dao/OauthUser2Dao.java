package com.wit.sc.oauth.web.dao;

import com.wit.sc.oauth.web.entity.Oauth2User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.oauth.web.dao
 * @Description:
 * @date 2019/8/2 16:06
 */
@Repository
public interface OauthUser2Dao extends JpaRepository<Oauth2User, String> {

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    Oauth2User getOauth2UserByUsername(String username);
}
