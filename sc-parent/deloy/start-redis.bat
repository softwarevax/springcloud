@echo off
set ENV_HOME="./Redis-x64-3.2.100"
cd %ENV_HOME%
start cmd /c redis-server.exe redis.windows.conf

set ENV_HOME="./../"
cd %ENV_HOME%