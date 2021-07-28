FROM openjdk:16
EXPOSE 8888
ADD /target/config-1.jar config.jar
ENTRYPOINT ["java", "-jar", "config.jar"]