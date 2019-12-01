package com.wit.sc.book.config.oauth;

import com.wit.sc.common.domain.constants.Constant;
import com.wit.sc.common.domain.dto.ResultDto;
import com.wit.sc.common.domain.entity.UserDetail;
import com.wit.sc.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ctw
 * @Project： springcloud
 * @Package: com.wit.sc.portal.config.oauth2
 * @Description:
 * @date 2019/12/1 15:09
 */
@Component
public class UserAuthenticationManage {

    @Autowired
    OAuth2RestTemplate restTemplate;

    public UserDetail userInfo() {
        ResponseEntity resp = restTemplate.getForEntity(Constant.USER_INFO_URL, ResultDto.class);
        ResultDto<UserDetail> dto = (ResultDto<UserDetail>) resp.getBody();
        if(!dto.isFlag()) {
            return new UserDetail();
        }
        return CommonUtils.mapToObject((Map<String, Object>) dto.getData(), UserDetail.class);
    }
}
