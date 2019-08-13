-- ----------------------------
-- Records of gateway
-- ----------------------------
INSERT INTO `gateway` VALUES ('1', 'lb://sc-portal', '[{\r\n	\"Path\": \"/sc-portal/**\"\r\n\r\n}]', '', '1', '1');
INSERT INTO `gateway` VALUES ('kylin', 'http://172.16.16.67:7070', '[{\"Path\":\"/kylin/**\",\"RemoteAddr\":\"10.4.127.133\"}]', '', '0', '2');

-- ----------------------------
-- Records of oauth2_client
-- ----------------------------
INSERT INTO `oauth2_client` VALUES ('1', 'sc-portal', '8000', 'http://localhost:8000/login,http://192.168.0.111:8000/login', 'authorization_code,client_credentials,password,implicit,refresh_token', 'sc-portal');
INSERT INTO `oauth2_client` VALUES ('2', 'sc-book', '8020', 'http://localhost:8020/login,http://192.168.0.111:8020/login', 'authorization_code,client_credentials,password,implicit,refresh_token', 'sc-book');

-- ----------------------------
-- Records of oauth2_user  账号密码明文:admin/1111
-- ----------------------------
INSERT INTO `oauth2_user` VALUES ('1', 'admin', '$2a$10$TFtERliDyvElycABUQecQOSCNgis6KvHiR/hlj0cuHPxVSEly7sai');


-- ----------------------------
-- Records of properties
-- ----------------------------
INSERT INTO `properties` VALUES ('17', 'spring.datasource.url', 'jdbc:mysql://localhost:3306/sc?serverTimezone=UTC&useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull&useSSL=false', 'sc-portal', 'dev', 'schema', '数据源url');
INSERT INTO `properties` VALUES ('18', 'spring.datasource.username', 'root', 'sc-portal', 'dev', 'schema', '数据源用户');
INSERT INTO `properties` VALUES ('19', 'spring.datasource.password', '123456', 'sc-portal', 'dev', 'schema', '数据源密码');
INSERT INTO `properties` VALUES ('20', 'spring.datasource.driver-class-name', 'com.mysql.jdbc.Driver', 'sc-portal', 'dev', 'schema', '数据源驱动');
INSERT INTO `properties` VALUES ('1', 'spring.redis.database', '0', 'sc-portal', 'dev', 'schema', 'Redis数据库索引（默认为0）');
INSERT INTO `properties` VALUES ('2', 'spring.redis.host', 'localhost', 'sc-portal', 'dev', 'schema', 'Redis服务器地址 默认为 localhost');
INSERT INTO `properties` VALUES ('3', 'spring.redis.port', '6379', 'sc-portal', 'dev', 'schema', 'Redis服务器连接端口 默认为6379');
INSERT INTO `properties` VALUES ('4', 'spring.redis.password', '', 'sc-portal', 'dev', 'schema', 'Redis服务器连接密码（默认为空）');
INSERT INTO `properties` VALUES ('5', 'spring.redis.timeout', '2000ms', 'sc-portal', 'dev', 'schema', '连接超时时间（毫秒）');


-- ----------------------------
-- Records of white_url
-- ----------------------------
INSERT INTO `white_url` VALUES ('12345', '192.168.0.111', '1');
INSERT INTO `white_url` VALUES ('2', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `white_url` VALUES ('3', '172.0.0.1', '1');