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

mybatis二级缓存:
```aidl
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
```aidl
1、启动ElasticSearch，运行elasticsearch-6.4.0/bin/elasticsearch.bat即可
head访问:localhost:9100 在目录elasticsearch-6.4.0\elasticsearch-head-master下执行“npm run start”插件启动
2、启动logstash，进入目录logstash-5.4.0\bin，执行命令:logstash -f std_std_es.conf
logstash访问:
3、启动kibana，运行kibana-6.1.2-windows-x86_64/bin/kibana.bat文件即可
kibana访问:http://localhost:5601
最好ElastciSearch版本和kibana版本可以保持一致
```

总结和说明:
```aidl
1、配置中心客户端一直提示“Fetching config from server at : http://localhost:8888”，但配置中心接口可用
    将客户端文件application.properties改为bootstrap.properties
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
15、自定义授权处理ResourceServerConfigurerAdapter配置(资源服务器,https://www.jianshu.com/p/19059060036b)
    //表单登录 方式
     http.formLogin().loginPage("/authentication/require")
     //登录需要经过的url请求
     .loginProcessingUrl("/authentication/form").successHandler(SavedRequestAwareAuthenticationSuccessHandler).failureHandler(SimpleUrlAuthenticationFailureHandler);
继承SimpleUrlAuthenticationFailureHandler可自定义登录失败后的处理，继承SavedRequestAwareAuthenticationSuccessHandler可自定义登录成功后的处理

16、服务间调用
16.1 所有接口设置路径前缀/api，用以区分页面和接口
16.2 请求接口未授权时，返回状态码为403的json数据
16.3 请求页面未授权时，重定向到登录页
16.4 微服务之间是接口的调用，普通用户请求的是页面，微服务之间的接口调用需要授权
17、全局异常捕获，无法捕获feign接口异常

``` 

###模块support-spring-boot-starter说明
```aidl

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

## 微服务系统模块记录
```aidl
1、注册中心：eureka-server(9000)
2、配置中心：config-server(9010)
3、监控中心：monitor-server(9020)
4、授权服务器：oauth2-server(9030,client和user基于jdbc,token基于redis)
5、链路跟踪：sleuth-server(9040)
6、路由网关：sc-gateway(8080,动态加载路由信息，暂未实现oauth授权)
7、门户为服务:sc-portal(8000)
8、书籍微服务:sc-book(8020)
9、辅助模块：support-spring-boot-starter(自定义starter)
10、服务间session共享(基于redis)
11、熔断器
12、日志入库、集成elk

```

### 开发记录
```aidl
第一次初步完成(未整理):2019/08/06 10:54
第二次初步完成(未整理，所有流程已通):2019/08/13 08:00

```

