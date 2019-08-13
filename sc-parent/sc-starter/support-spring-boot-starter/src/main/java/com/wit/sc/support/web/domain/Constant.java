package com.wit.sc.support.web.domain;

/**
 * @author ctw
 * @Title: Constant
 * @ProjectName sc-parent
 * @Description: 常亮
 * @date 2019/1/5/00519:51
 */
public class Constant {

    /**
     * 记录一次request请求轨迹(方法的调用顺序)
     */
    public static final String REQUEST_TRACK_ID  = "trackId";

    public static final String ASPECT_METHOD_ID  = "methodId";

    public static final String REQUEST_USER_AGENT  = "User-Agent";

    public static final String REQUEST_HOST  = "Host";

    /**
     * 接口返回的字符串的最大长度,20M
     */
    public static final int MAX_RESULT_SIZE = 20 * 1024 * 1024;

    public static final String PARAMTER_INSERT_SQL = "INSERT INTO `t_params` (`id`,`t_obj_id`,`t_obj_val`,`t_obj_type`,`t_obj_idx`) VALUES(?, ?, ?, ?, ?)";

    public static final String METHOD_RECORDINSERT_SQL = "INSERT INTO `t_method_record` ( `t_method_id`,`t_thread_name`,`t_start_time`,`t_end_time`,`t_class_name`,`t_modifier`,`t_method_name`,`t_args_id`,`t_result_id`,`t_track_id`,`t_user_agent`,`t_server_host`,`t_url`,`t_method_type`,`t_client_host`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String PARAMTER_QUERY_SQL = "SELECT t_obj_id from `t_params` WHERE t_obj_id = ?";

    public static final String METHOD_QUERY_SQL = "SELECT t_method_id from `t_method_record` WHERE t_method_id = ?";
}
