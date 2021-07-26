FROM openjdk:16
EXPOSE 9000 8761
ADD target/eureka-server-1.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]