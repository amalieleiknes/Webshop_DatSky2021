# which jdk we are using
FROM openjdk:14
ADD target/user-mysql.jar user-mysql.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "user-mysql.jar"]
