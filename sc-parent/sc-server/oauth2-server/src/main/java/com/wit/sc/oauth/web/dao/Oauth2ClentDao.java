package com.wit.sc.oauth.web.dao;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.oauth.web.dao
 * @Description:
 * @date 2019/8/10 17:03
 */
/*@Repository
public interface Oauth2ClentDao  extends JpaRepository<Oauth2Client, String> {
    *//**
     * 查询第三方应用信息
     * @param clientId
     * @return
     *//*
    Oauth2Client getOauth2ClientByClientId(String clientId) {
        String sql = "select * from oauth2_client where clientId = ?";
        return jdbcTemplate.query(sql, new String[]{clientId}, new BeanPropertyRowMapper<>(Oauth2Client.class));
    }


}*/
