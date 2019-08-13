package com.wit.sc.oauth.web.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.oauth.web.entity
 * @Description: 授权的用户，新增时，用户密码需要经过加密
 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
 * 		String password = passwordEncoder.encode("134233");
 * @date 2019/8/2 16:06
 */
@Entity(name = "oauth2_user")
public class Oauth2User implements UserDetails, Serializable {

    @Id
    private int id;

    private String username;

    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
