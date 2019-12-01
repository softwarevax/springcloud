package com.wit.sc.oauth.web.service;

import com.wit.sc.oauth.web.dao.UserDetailDao;
import com.wit.sc.oauth.web.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ctw
 * @Project： springcloud
 * @Package: com.wit.sc.oauth.web.service
 * @Description:
 * @date 2019/12/1 12:16
 */
@Service
public class UserDetailService {

    @Autowired
    UserDetailDao userDetailDao;

    public UserDetail getUserInfo(String userId) {
        return userDetailDao.getUserInfoByUserId(userId);
    }
}
