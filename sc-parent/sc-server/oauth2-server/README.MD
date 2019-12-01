### 1、统一认证平台
##### 独立模块，统一认证模块，不集成其他模块，提供用户信息和登录认证

##### 1.1 集成注册中心
> 1.1.1 在pom文件添加依赖
```aidl
<!-- 注册到注册中心 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
> 1.1.2 在启动类OauthServerApplication上添加注解@EnableEurekaClient

> 1.1.3 在属性配置文件中添加配置
```aidl
eureka.server.node1=localhost:9000
eureka.server.node2=localhost:9001
spring.security.user.name=admin
spring.security.user.password=1111
eureka.client.service-url.defaultZone=http://${spring.security.user.name}:${spring.security.user.password}@${eureka.server.node1}/eureka/,http://${spring.security.user.name}:${spring.security.user.password}@${eureka.server.node2}/eureka/

eureka.instance.prefer-ip-address=true
eureka.instance.instance-id=${spring.application.name}:${server.port}
eureka.instance.lease-expiration-duration-in-seconds=3
eureka.instance.lease-renewal-interval-in-seconds=1
```

##### 1.2 集成elk日志
###### 1.2.1 pom添加依赖
```aidl
<!-- elk管理日志 -->
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <version>5.1</version>
</dependency>
```
###### 1.2.2 修改日志文件路径
> logging.config = classpath:logback-spring.xml  ===> logging.config = classpath:elk-logback-spring.xml

###### 1.2.3 启动elk
运行脚本start-elk.bat