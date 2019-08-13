package com.wit.sc.support.web.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.domain.entity
 * @Description:
 * @date 2019/6/15 14:51
 */
@Data
public class Paramter implements Serializable {

    private String id;

    private String objId;

    private String objValue;

    private String objType;

    private Integer objIdx;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
