FROM openjdk:16
EXPOSE 9000 8761
ADD target/eureka-1.jar eureka.jar
ENTRYPOINT ["java", "-jar", "/eureka.jar"]