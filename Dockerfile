# which jdk we are using
FROM openjdk:14
ADD out/artifacts/packages_jar/packages.jar myjar.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "myjar.jar"]