# 基础镜像
FROM openjdk:17-jdk-alpine
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo "$TZ" > /etc/timezone

# 拷贝jar包
# 这里要注意前面是宿主机上的jar包的名字
COPY nytd-forum-0.0.1-SNAPSHOT.jar /app.jar 
# 入口
ENTRYPOINT ["java", "-jar", "/app.jar"]
