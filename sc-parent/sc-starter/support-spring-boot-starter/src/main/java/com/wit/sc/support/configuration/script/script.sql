DROP TABLE IF EXISTS `t_method_record`;
CREATE TABLE `t_method_record` (
  `t_method_id` varchar(32),
  `t_thread_name` varchar(50) ,
  `t_start_time` bigint(30),
  `t_end_time` bigint(30),
  `t_class_name` varchar(200),
  `t_modifier` int(5),
  `t_method_name` varchar(200),
  `t_args_id` varchar(32),
  `t_result_id` varchar(32),
  `t_track_id` varchar(64),
  `t_user_agent` varchar(2000),
  `t_server_host` varchar(50),
  `t_url` varchar(2000),
  `t_method_type` varchar(50),
  `t_client_host` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `t_params`;
CREATE TABLE `t_params` (
  `id` varchar(32),
  `t_obj_id` varchar(32),
  `t_obj_val` longtext,
  `t_obj_type` varchar(200),
  `t_obj_idx` int(11),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
