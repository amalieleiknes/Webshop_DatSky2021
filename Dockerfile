# which jdk we are using
FROM openjdk:14
VOLUME /tmp
EXPOSE 10222
ADD target/webshop-mysql.jar webshop-mysql.jar
ENTRYPOINT ["java", "-jar", "webshop-mysql.jar"]
