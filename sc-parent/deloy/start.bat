@echo off
::启动注册中心集群
start cmd /c java -jar -Dspring.profiles.active=node1 registry-server.jar
start cmd /c java -jar -Dspring.profiles.active=node2 registry-server.jar

::启动配置中心
start cmd /c java -jar config-server.jar

::启动授权服务器
start cmd /c java -jar oauth2-server.jar

::启动redis
start cmd /c start-redis.bat

::启动elk
::start cmd /c start-elk.bat