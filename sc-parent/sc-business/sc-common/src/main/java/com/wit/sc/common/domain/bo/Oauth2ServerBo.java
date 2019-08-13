package com.wit.sc.common.domain.bo;

import lombok.Data;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.domain.bo
 * @Description:
 * @date 2019/8/2 18:08
 */
@Data
public class Oauth2ServerBo {

    private String serverAddr;

    private String tokenAuthorizeUrl;
}
