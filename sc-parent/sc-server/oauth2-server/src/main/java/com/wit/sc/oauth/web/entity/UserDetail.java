package com.wit.sc.oauth.web.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ctw
 * @Project： springcloud
 * @Package: com.wit.sc.oauth.web.entity
 * @Description: 用户详情
 * @date 2019/12/1 11:42
 */
@Entity(name = "t_user_detail")
public class UserDetail {

    @Id
    private String userId;

    private String userName;

    private String nickName;

    private String mobile;

    private String balance;

    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
