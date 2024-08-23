FROM tomcat:9.0.89-jdk17

RUN ["rm", "/etc/localtime"]
RUN ["ln", "-sf", "/usr/share/zoneinfo/Asia/Seoul", "/etc/localtime"]

COPY ./lib/mysql-connector-j-8.0.33.jar /usr/local/tomcat/lib/mysql-connector-j-8.0.33.jar
COPY ./target/recruit-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

#start tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]