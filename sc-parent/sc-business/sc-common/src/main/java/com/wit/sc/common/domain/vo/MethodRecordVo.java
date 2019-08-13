package com.wit.sc.common.domain.vo;

import com.alibaba.fastjson.JSON;
import com.wit.sc.common.domain.entity.MethodRecord;
import com.wit.sc.common.domain.entity.Paramter;
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

    PageArgs pageArgs;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
