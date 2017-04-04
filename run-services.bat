@echo off
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_101
set PATH=%JAVA_HOME%\bin;%PATH%;

echo 'Starting friends server: '
start cmd /K java -jar friends-server/target/friends-server-1.0-SNAPSHOT.jar
echo 'Press any key to start timeline-server'
pause
start cmd /K java -jar timeline-server/target/timeline-server-1.0-SNAPSHOT.jar
echo 'Press any key to start user-server'
pause

start cmd /K java -jar user-server/target/user-server-1.0-SNAPSHOT.jar
echo 'Press any key to start web-server'
pause

start cmd /K java -jar web-server/target/web-server-1.0-SNAPSHOT.jar