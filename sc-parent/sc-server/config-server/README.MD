### 1、配置中心
##### 1.1 集成elk日志
###### 1.1.1 pom添加依赖
```aidl
<!-- elk管理日志 -->
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <version>5.1</version>
</dependency>
<dependency>
    <groupId>commons-dbcp</groupId>
    <artifactId>commons-dbcp</artifactId>
    <version>1.4</version>
</dependency>
<dependency>
    <groupId>commons-pool</groupId>
    <artifactId>commons-pool</artifactId>
    <version>1.6</version>
</dependency>
```
###### 1.1.2 修改日志文件路径
> logging.config = classpath:logback-spring.xml  ===> logging.config = classpath:elk-logback-spring.xml

###### 1.1.3 启动elk
运行脚本start-elk.bat