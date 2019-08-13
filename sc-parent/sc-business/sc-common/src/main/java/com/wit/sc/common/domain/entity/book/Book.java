package com.wit.sc.common.domain.entity.book;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author twcao
 * @Title: Book
 * @ProjectName sc-parent
 * @Description: 书的实体类-对应数据库表
 * @date 2019/1/6/00615:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    /**
     * 书的id
     * 书与商店的E-R关系: n <-----> 1
     */
    private String bookId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 书的作者名字
     */
    private String author;

    /**
     * 书的库存
     */
    private String inventory;

    /**
     * 书的介绍说明
     */
    private String introduce;

    /**
     * 书的售价
     */
    private Double price;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
