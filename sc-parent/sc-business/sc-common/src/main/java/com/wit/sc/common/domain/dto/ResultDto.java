package com.wit.sc.common.domain.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author ctw
 * @Title: ResultDto
 * @ProjectName sc-parent
 * @Description: 接口结果包装实体
 * @date 2019/1/6/00616:10
 */
public class ResultDto<T> {

    /**
     * 接口调用返回状态
     */
    public enum Status{
        /**
         * 返回成功或失败的messgae
         */
        SUCCESS("接口调用成功"), FAIL("接口调用失败");

        private String value;

        Status(String message) {
            this.value = message;
        }

        public String value() {
            return this.value;
        }
    }

    /**
     * 结果标识
     */
    private boolean flag;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 私有化构造函数
     */
    private ResultDto() {}

    public ResultDto(boolean flag, T data, String message) {
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public static <T> String result(boolean flag, T data, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.flag = flag;
        resultDto.data = data;
        resultDto.message = message;
        //将值为null的字段设置为"",将值为null的list设置为[]
        return JSON.toJSONString(resultDto, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
    }

    public static String result(boolean flag) {
        return result(flag, null, flag ? Status.SUCCESS.value : Status.FAIL.value);
    }

    public static String result(boolean flag, String message) {
        return result(flag, null, message);
    }

    public static <T> String success(T data, String message) {
        return result(true, data, message);
    }

    public static <T> String success(T data) {
        return success(data, Status.SUCCESS.value);
    }

    public static String success() {
        return success(null, Status.SUCCESS.value);
    }

    public static String success(String message) {
        return success(null, message);
    }

    public static <T> String fail(T data, String message) {
        return result(false, data, message);
    }

    public static <T> String fail(T data) {
        return fail(data, Status.FAIL.value);
    }

    public static String fail(String message) {
        return fail(null, message);
    }

    public static String fail() {
        return fail(null, Status.FAIL.value);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> ResultDto<T> successT(T data) {
        ResultDto<T> resultDto = new ResultDto<>();
        resultDto.data = data;
        resultDto.flag = true;
        resultDto.message = Status.SUCCESS.value;
        return resultDto;
    }

    public static <T> ResultDto<T> failT(T data) {
        ResultDto<T> resultDto = new ResultDto<>();
        resultDto.data = data;
        resultDto.flag = false;
        resultDto.message = Status.FAIL.value;
        return resultDto;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
