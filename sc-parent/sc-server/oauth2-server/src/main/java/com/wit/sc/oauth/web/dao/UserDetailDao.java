package com.wit.sc.oauth.web.dao;

import com.wit.sc.oauth.web.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ctw
 * @Project： springcloud
 * @Package: com.wit.sc.oauth.web.dao
 * @Description:
 * @date 2019/12/1 12:15
 */
@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, String> {

    UserDetail getUserInfoByUserId(String userId);
}
