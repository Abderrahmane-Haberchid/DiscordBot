
FROM tomcat:9-jdk17-openjdk-slim
WORKDIR /usr/local/tomcat/webapps

COPY target/botTP.war /usr/local/tomcat/webapps/botTP.war

EXPOSE 8080


