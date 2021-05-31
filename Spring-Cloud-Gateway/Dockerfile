FROM openjdk:8
EXPOSE 8080
COPY . /app
WORKDIR /app
ADD target/Spring-Cloud-Gateway-0.0.1-SNAPSHOT.jar spring-cloud-gateway.jar
ENTRYPOINT ["java", "-jar", "/spring-cloud-gateway.jar"]
CMD java -jar spring-cloud-gateway.jar
