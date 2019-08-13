package com.wit.sc.support.web.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.domain.vo
 * @Description:
 * @date 2019/6/15 23:04
 */
@Data
public class MethodRecordVo {
    MethodRecord method;
    Paramter paramter;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
