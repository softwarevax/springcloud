## 1、微服务系统
### 1.1、高可用注册中心：eureka-server(9000)
#### 1.1.1、 eureka.instance.prefer-ip-address
> eureka.instance.prefer-ip-address=true
#### 1.1.2、 eureka.instance.instance-id
> 不要配置，以便能更好的区分不同的注册中心
#### 1.1.3、 服务治理
> https://github.com/Netflix/eureka/wiki/Eureka-REST-operations
#### 1.1.4、访问地址
> http://ip:port/9000(admin/1111)
### 1.2、高可用配置中心：config-server(9010)
#### 1.2.1、设置应用名
> spring.application.name=config-server
#### 1.2.2、自定义配置中心
> 通过实现接口EnvironmentRepository
#### 1.2.3、访问地址(ip:port/application/profile/schema)
> http://ip:9010/sc-portal/dev/schema(admin/1111), 默认返回json格式, 通过添加依赖
```
<dependency>
    <groupId>com.fasterxml.jackson.jaxrs</groupId>
    <artifactId>jackson-jaxrs-xml-provider</artifactId>
</dependency>
```
可以返回xml格式数据，便于观察
#### 1.2.4、客户端配置
```
spring.cloud.config.username=
spring.cloud.config.password=
spring.cloud.config.discovery.service-id=config-server
# service-id和spring.cloud.config.uri任选其一, 使用service-id可以支持动态添加配置中心
# spring.cloud.config.uri=http://localhost:9010,http://localhost:9011
spring.cloud.config.discovery.enabled=true
spring.cloud.config.profile=dev
spring.cloud.config.label=schema
```
### 1.3、监控中心：monitor-server(9020)
#### 1.3.1、访问地址
> http://ip:9020(admin/1111), eureka客户端集成存在问题
### 1.4、授权服务器：oauth2-server(9030)
> client和user基于jdbc,token基于redis
#### 1.4.1、客户端集成
##### 1.4.1.1、继承OauthClientSecurityConfigure
> 注解@EnableOAuth2Sso、@Configuration、@EnableOAuth2Client, 若是系统入口, 则不添加注解@EnableResourceServer, 如网管应用, 将@EnableResourceServer注解去掉
```
auth-server=http://ip:9030/auth
security.oauth2.client.client-id=
security.oauth2.client.client-secret=
security.oauth2.client.access-token-uri=${auth-server}/oauth/token
security.oauth2.client.user-authorization-uri=${auth-server}/oauth/authorize
security.oauth2.resource.user-info-uri=${auth-server}/user
```
### 1.5、链路跟踪：sleuth-server(9040)
#### 1.5.1、访问地址
> http://ip:9040
### 1.6、路由网关：sc-gateway(8080)
> 动态加载路由信息，暂未实现oauth授权
### 1.7、门户微服务:sc-portal(8000)
### 1.8、书籍微服务:sc-book(8020)
### 1.9、其他
#### 1.9.1、辅助模块：support-spring-boot-starter(自定义starter)
#### 1.9.2、服务间session共享(基于redis)
#### 1.9.3、熔断器
#### 1.9.4、日志入库、集成elk
```

说明:redis、logstash、elasticsearch、kibana文件已被删除，请自行下载相应的插件到对应的目录下，方便启动
feign的请求超时时间较短，可适当设置长些
```aidl
项目启动顺序:
1、sc-parent根目录运行:mvn install -DskipTests
打包相应文件，把需要提前启动的包(sc-server下的包)打进deloy目录
2、deloy目录下运行:start start.bat
以启动注册中心、配置中心、授权服务器、redis、logstash、elasticsearch、kibana
3、待服务已全部注册完成，再运行其他服务，如：monitor-server、sleuth-server、sc-book、sc-portal、sc-zuul
```

## 2、mybatis二级缓存:
```
1.映射语句文件中所有的select语句将会被缓存
2.映射语句文件中所有的insert update delete语句会刷新缓存
3.缓存会使用(Least Flush Interval,LRU最近最少使用的)算法来收回
4.根据时间表（如 no Flush Interval,没有刷新间隔），缓存不会以任何时间顺序来刷新
5.缓存会存储集合或对象（无论查询方法返回什么类型的值）的1024个引用
6.缓存会被视为read/wriete(可读/可写)的，意味着对象检索不是共享的，而且可以安全的被调用者修改，而不干扰其他调用者或者线程所做的潜在修改。

<cache eviction="LRU" flushInterval="60000" size="512" readOnly="true"/>
配置属性详解如下
eviction回收策略:
1.LRU 最近最少使用的，移除最长时间不被使用的对象，这是默认值
2.FIFO 先进先出，按对象进入缓存的顺序来移除它们
3.SOFT 软引用，移除基于垃圾回收器状态和软引用规则的对象
4.WEAK 弱引用，更积极的移除基于垃圾收集器状态和弱引用规则的对象
flushInterval刷新间隔
可以被设置为任意的正整数，而且它们代表一个合理的毫秒形式的时间段。 默认情况不设置，即没有刷新间隔，缓存仅仅在调用语句时刷新
size引用数目
可以被设置为任意的正整数，要记住缓存的对象数目和运行环境的可用内存资源数目，默认1024
readOnly只读
属性可以被设置为true后者false。 只读的缓存会给所有调用者返回缓存对象的相同实例，因此这些对象不能被修改，这提供了很重要的性能优势。 可读写的缓存会通过序列化返回缓存对象的拷贝，这种方式会慢一些，但很安全，因此默认为false
```
ELK启动:
```
1、启动ElasticSearch，运行elasticsearch-6.4.0/bin/elasticsearch.bat即可
head访问:localhost:9100 在目录elasticsearch-6.4.0\elasticsearch-head-master下执行“npm run start”插件启动
2、启动logstash，进入目录logstash-5.4.0\bin，执行命令:logstash -f std_std_es.conf
logstash访问:
3、启动kibana，运行kibana-6.1.2-windows-x86_64/bin/kibana.bat文件即可
kibana访问:http://localhost:5601
最好ElastciSearch版本和kibana版本可以保持一致
```

## 3、总结和说明:
```
1、配置中心客户端一直提示“Fetching config from server at : http://localhost:8888”，但配置中心接口可用
   解决办法: 将客户端文件application.properties改为bootstrap.properties
2、jvm参数设置:
    -XX:+PrintGCDetails 打印gc日志
3、批量新增设置rewriteBatchedStatements=true&useServerPrepStmts=true
4、PioneerInterceptor 分页设置拦截器，限定分页的最大值，避免分页太大导致服务器查询缓慢
   PioneerFilter 设置请求轨迹
   Register 注册所有的bean
   GlobalAdviceController 全局异常处理器
   DistributedLockHandler 分布式锁(redis)
   LogDBAppender 日志持久化到数据库 logback-spring.xml(DB_APPENDER)
5、切面
    MethodAspect： 方法切面，处理所有符合切点的方法。所有方法的入口均会打印具体的日志(格式: timestamp context className.methodName(args...))
    注： 切面不能包含切面处理的方法，否则会导致循环
    ResultAspect： 结果切面，所有符合切点的方法的返回结果，均会被处理，如返回结果为1，则会返回ResultDto.success(data);
    注：切点的优先级设置到最小(Integer.MAX_VALUE),因为返回结果是最后返回的，如果有异常，会被全局异常捕获，不会进入该切面
6、Error creating bean with name 'scopedTarget.oauth2ClientContext': Scope 'session' is not active
    add a bean annotation into your app
    解决办法:
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
7、如果support.method.resultWrapEnable=true,则所有接口的返回类型只能为ResultDto.success(data)返回类型的超类(Object)
8、oauth预留用户注册，用户修改，用户注销接口
9、需要授权的服务，monitor目前无法监控（被监控的服务好像不能设置contxetPath）
10、监控服务 localhost:9020
11、链路跟踪器：localhost:9040
nohup java -jar -Dspring.profiles.active=node1 -Xms64m -Xmx64m registry-server.jar > registry-server.out 2>&1 &
nohup java -jar -Xms64m -Xmx64m config-server.jar > config-server.out 2>&1 &
nohup java -jar -Xms64m -Xmx64m sleuth-server.jar > sleuth-server.out 2>&1 &
echo 3 > /proc/sys/vm/drop_caches
12、Unregistering application SC-BOOK with eureka with status DOWN 缺少包的导入:spring-boot-starter-web
13、如果oauth2用户信息存入数据库，则与数据库用户表对应的实体，必须继承UserDetails，而不能使用匿名类，否则会造成无法反序列化

14、重定义不合法请求
 @EnableResourceServer
/**
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler((request,response,e)->{
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(new ObjectMapper().writeValueAsString("授权失败"));
        });
        resources.authenticationEntryPoint((request,response,e)->{
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(new ObjectMapper().writeValueAsString("token失败"));
        });
    }

``` 

###模块support-spring-boot-starter说明
```
1、依赖包:
<dependency>
    <groupId>com.wit.sc</groupId>
    <artifactId>support-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
2、添加@EnableSupport注解

完成如下功能:
1、限定分页大小：默认限制为pageSize = 50
2、白名单限制，没有配置在白名单内的主机无法访问
3、将方法的参数、返回结果存入数据库，记录方法详情
4、日志存入到数据库（logback）
5、统一处理返回结果，结果为成功返回的结果，但是要求所有的接口返回Object类型。如果是返回页面，则在controller目录下新建page目录

# 以下配置均为默认值
support.method.argsRrecordEnable=true
support.method.detailRecordEnable=true
support.method.resultWrapEnable=true
#配置过滤器
#是否启动 "pioneerFilter"不能修改
support.filters[pioneerFilter].enable=true
# 配置分页大小  pioneerFilter和pageSize不能修改，默认为50
# support.filters[pioneerFilter].properties[pageSize]=50
# 设置白名单 如果使用ip访问，则需要配置ip地址，多个之间都好分隔
support.filters[pioneerFilter].properties[whiteUrls]=0:0:0:0:0:0:0:1,127.0.0.1
```

### 开发记录
```
第一次初步完成(未整理):2019/08/06 10:54
第二次初步完成(未整理，所有流程已通):2019/08/13 08:00
第三次整理依赖包，依赖包版本由dependencies-bom统一管理(导入后，若无法识别jar包版本，先将"dependencies-bom" install) 2020/05/05 13:40
```

