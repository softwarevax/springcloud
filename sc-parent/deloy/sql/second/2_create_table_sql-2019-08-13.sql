/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : sc

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2019-08-13 08:16:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gateway
-- ----------------------------
DROP TABLE IF EXISTS `gateway`;
CREATE TABLE `gateway` (
  `id` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT 'id，不设置则使用uuid',
  `uri` varchar(255) COLLATE utf8_bin NOT NULL,
  `predicates` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '转发的uri，如:www.baidu.com',
  `filters` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '过滤',
  `enabled` tinyint(1) NOT NULL,
  `order` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event` (
  `timestmp` bigint(20) NOT NULL,
  `formatted_message` text COLLATE utf8_unicode_ci NOT NULL,
  `logger_name` varchar(254) COLLATE utf8_unicode_ci NOT NULL,
  `level_string` varchar(254) COLLATE utf8_unicode_ci NOT NULL,
  `thread_name` varchar(254) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `arg0` varchar(254) COLLATE utf8_unicode_ci DEFAULT NULL,
  `arg1` varchar(254) COLLATE utf8_unicode_ci DEFAULT NULL,
  `arg2` varchar(254) COLLATE utf8_unicode_ci DEFAULT NULL,
  `arg3` varchar(254) COLLATE utf8_unicode_ci DEFAULT NULL,
  `caller_filename` varchar(254) COLLATE utf8_unicode_ci NOT NULL,
  `caller_class` varchar(254) COLLATE utf8_unicode_ci NOT NULL,
  `caller_method` varchar(254) COLLATE utf8_unicode_ci NOT NULL,
  `caller_line` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=162979 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception` (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`event_id`,`i`),
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property` (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254) COLLATE utf8_unicode_ci NOT NULL,
  `mapped_value` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`event_id`,`mapped_key`),
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for oauth2_user
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_user`;
CREATE TABLE `oauth2_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientId` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `resource_ids` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_secret` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authorized_grant_types` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authorities` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `autoapprove` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `key` varchar(500) COLLATE utf8_bin NOT NULL,
  `value` varchar(500) COLLATE utf8_bin NOT NULL DEFAULT '',
  `application` varchar(1000) COLLATE utf8_bin NOT NULL,
  `profile` varchar(50) COLLATE utf8_bin NOT NULL,
  `label` varchar(50) COLLATE utf8_bin NOT NULL,
  `remark` varchar(1000) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for sc_book
-- ----------------------------
DROP TABLE IF EXISTS `sc_book`;
CREATE TABLE `sc_book` (
  `book_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `price` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `discount` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `inventory` int(11) DEFAULT NULL,
  `introduce` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_method_record
-- ----------------------------
DROP TABLE IF EXISTS `t_method_record`;
CREATE TABLE `t_method_record` (
  `t_method_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `t_thread_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `t_start_time` bigint(14) NOT NULL,
  `t_end_time` bigint(14) NOT NULL,
  `t_class_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `t_modifier` int(5) DEFAULT NULL,
  `t_method_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `t_args_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `t_result_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `t_track_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `t_user_agent` varchar(2000) COLLATE utf8_unicode_ci DEFAULT '',
  `t_server_host` varchar(50) COLLATE utf8_unicode_ci DEFAULT '',
  `t_url` varchar(2000) COLLATE utf8_unicode_ci DEFAULT '',
  `t_method_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT '',
  `t_client_host` varchar(50) COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`t_method_id`),
  KEY `t_args_id` (`t_args_id`),
  KEY `t_result_id` (`t_result_id`),
  KEY `t_start_time` (`t_start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for t_params
-- ----------------------------
DROP TABLE IF EXISTS `t_params`;
CREATE TABLE `t_params` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `t_obj_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `t_obj_val` longtext COLLATE utf8_unicode_ci,
  `t_obj_type` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `t_obj_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_obj_id` (`t_obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for white_url
-- ----------------------------
DROP TABLE IF EXISTS `white_url`;
CREATE TABLE `white_url` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `ip` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '可以访问网关的ip列表，不支持表达式',
  `is_enable` tinyint(1) DEFAULT '1' COMMENT '白名单是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
