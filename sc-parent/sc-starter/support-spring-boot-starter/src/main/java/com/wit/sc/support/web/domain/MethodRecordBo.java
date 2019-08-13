package com.wit.sc.support.web.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.domain.bo
 * @Description:
 * @date 2019/6/15 14:54
 */
@Data
public class MethodRecordBo extends MethodRecord implements Serializable {

    private List<Paramter> args;

    private List<Paramter> result;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
