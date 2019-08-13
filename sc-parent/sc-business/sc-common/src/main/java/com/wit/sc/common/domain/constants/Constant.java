package com.wit.sc.common.domain.constants;

/**
 * @author ctw
 * @Title: Constant
 * @ProjectName sc-parent
 * @Description: 常亮
 * @date 2019/1/5/00519:51
 */
public class Constant {

    public static String REQUEST_TRACK_ID  = "trackId";

    public static String ASPECT_METHOD_ID  = "methodId";

    public static String REQUEST_USER_AGENT  = "User-Agent";

    public static String REQUEST_HOST  = "Host";

    /**
     * 接口返回的字符串的最大长度,20M
     */
    public static int MAX_RESULT_SIZE = 20 * 1024 * 1024;

    /**
     * 最大的请求分页数：50
     */
    public static int MAX_PAGE_SIZE = 50;

    public static String PAGE_SIZE_PARAM = "pageSize";
}
