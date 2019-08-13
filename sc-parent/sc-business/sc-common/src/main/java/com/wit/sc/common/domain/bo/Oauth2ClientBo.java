package com.wit.sc.common.domain.bo;

import lombok.Data;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.domain.entity
 * @Description:
 * @date 2019/8/2 17:54
 */
@Data
public class Oauth2ClientBo {

    private String clientId;

    private String clientSecret;

    private String grantType;

    private String scope;

    private String redirectUri;
}
