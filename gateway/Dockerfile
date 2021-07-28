FROM openjdk:16
EXPOSE 9001
ADD /target/gateway-1.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]