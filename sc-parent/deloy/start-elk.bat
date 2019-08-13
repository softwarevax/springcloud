@echo off

::启动es
set ENV_HOME="./elasticsearch-6.4.0/bin"
cd %ENV_HOME%
start cmd /c elasticsearch.bat

::启动logstash
set ENV_HOME="./../../logstash-5.4.0/bin"
cd %ENV_HOME%
start cmd /c logstash -f std_std_es.conf

::启动kibana
set ENV_HOME="./../../kibana-6.1.2-windows-x86_64/bin"
cd %ENV_HOME%
start cmd /c kibana.bat

set ENV_HOME="./../../"
cd %ENV_HOME%