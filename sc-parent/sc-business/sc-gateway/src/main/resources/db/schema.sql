-- ----------------------------
-- Table structure for gateway_definition
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
INSERT INTO `gateway` VALUES ('1', 'lb://sc-portal', '[{\r\n	\"Path\": \"/sc-portal/**\"\r\n\r\n}]', '', '0', '1');
INSERT INTO `gateway` VALUES ('kylin', 'http://172.16.16.67:7070', '[{\"Path\":\"/kylin/**\",\"RemoteAddr\":\"10.4.127.133\"}]', '', '0', '2');


-- ----------------------------
-- Table structure for sc_white_url
-- ----------------------------
DROP TABLE IF EXISTS `white_url`;
CREATE TABLE `white_url` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `ip` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '可以访问网关的ip列表，不支持表达式',
  `is_enable` tinyint(1) DEFAULT '1' COMMENT '白名单是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
INSERT INTO `white_url` VALUES ('12345', '10.4.127.133', '1');