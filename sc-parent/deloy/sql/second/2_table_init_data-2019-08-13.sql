INSERT INTO `gateway` VALUES ('1', 'lb://sc-portal', '[{\r\n	\"Path\": \"/sc-portal/**\"\r\n\r\n}]', '', '1', '1');
INSERT INTO `gateway` VALUES ('kylin', 'http://172.16.16.67:7070', '[{\"Path\":\"/kylin/**\",\"RemoteAddr\":\"10.4.127.133\"}]', '', '0', '2');


INSERT INTO `oauth_client_details` VALUES ('sc-book', null, '{bcrypt}$2a$10$fYepfWbzMtGI/U798XAqfeq.HthQ/AGtAUlhfU4l3Xj0EkHML3EEu', 'sc-book', 'authorization_code,client_credentials,password,implicit,refresh_token', 'http://localhost:8020/login,http://192.168.0.111:8020/login', null, null, null, null, 'true');
INSERT INTO `oauth_client_details` VALUES ('sc-portal', '', '{bcrypt}$2a$10$BJSW4rH/XI8AogtlqKKNbeA0NvWXO.QDJ2IQX7nWetV.lGvaMlln6', 'sc-portal', 'authorization_code,client_credentials,password,implicit,refresh_token', 'http://localhost:8000/login,http://192.168.0.111:8000/login', null, null, null, null, 'true');
INSERT INTO `oauth_client_details` VALUES ('sc-zuul', null, '{bcrypt}$2a$10$Pw.PFZG4q/zEkRIwp8AaUuvuG1Q7pkHV8lStqWlnmMA1zpsd1asEK', 'sc-zuul', 'authorization_code,client_credentials,password,implicit,refresh_token', 'http://localhost:8080/login,http://192.168.0.111:8080/login', null, null, null, null, 'true');


-- 密码明文为1111
INSERT INTO `oauth2_user` VALUES ('1', 'admin', '{bcrypt}$2a$10$L0vDtlNJVkQRphlfsJHeXO.ALEMFp5RTiRqUGOGdOIoBVVJeBdpcC');

INSERT INTO `properties` VALUES ('17', 'spring.datasource.url', 'jdbc:mysql://localhost:3306/sc?serverTimezone=UTC', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', '数据源url');
INSERT INTO `properties` VALUES ('18', 'spring.datasource.username', 'root', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', '数据源用户');
INSERT INTO `properties` VALUES ('19', 'spring.datasource.password', '123456', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', '数据源密码');
INSERT INTO `properties` VALUES ('20', 'spring.datasource.driver-class-name', 'com.mysql.jdbc.Driver', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', '数据源驱动');
INSERT INTO `properties` VALUES ('1', 'spring.redis.database', '0', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', 'Redis数据库索引（默认为0）');
INSERT INTO `properties` VALUES ('2', 'spring.redis.host', 'localhost', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', 'Redis服务器地址 默认为 localhost');
INSERT INTO `properties` VALUES ('3', 'spring.redis.port', '6379', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', 'Redis服务器连接端口 默认为6379');
INSERT INTO `properties` VALUES ('4', 'spring.redis.password', '', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', 'Redis服务器连接密码（默认为空）');
INSERT INTO `properties` VALUES ('5', 'spring.redis.timeout', '2000ms', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', '连接超时时间（毫秒）');
INSERT INTO `properties` VALUES ('6', 'support.filters[pioneerFilter].properties[whiteUrls]', '192.168.0.111', 'sc-portal,sc-zuul,sc-book', 'dev', 'schema', '添加了support模块设置的白名单');


INSERT INTO `sc_book` VALUES ('1', 'Spring Boot编程思想（核心篇）', '小马哥', '96.40', '8.2', '100', '');
INSERT INTO `sc_book` VALUES ('2', '深入浅出Spring Boot 2.x', '杨开振', '89.10', '9.0', '100', '');
INSERT INTO `sc_book` VALUES ('3', 'Spring Boot实战', '克雷格·沃斯（Craig Walls', '46.60', '7.9', '100', '');
INSERT INTO `sc_book` VALUES ('4', 'JavaEE开发的颠覆者：Spring Boot实战', '汪云飞', '61.40', '6.9', '100', '');


INSERT INTO `white_url` VALUES ('12345', '192.168.0.111', '1');
INSERT INTO `white_url` VALUES ('2', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `white_url` VALUES ('3', '172.0.0.1', '1');
