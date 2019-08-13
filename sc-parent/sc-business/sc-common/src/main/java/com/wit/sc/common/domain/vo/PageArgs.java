package com.wit.sc.common.domain.vo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.common.domain.vo
 * @Description:
 * @date 2019/6/16 12:33
 */
@Data
public class PageArgs<T> implements Serializable {

    int pageSize = 10;

    /**
     * 页码从1开始
     */
    int pageNo = 1;

    T data;

    int count;

    int start;

    public int getStart() {
        return (this.pageNo - 1) * this.pageSize;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
