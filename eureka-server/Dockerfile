FROM openjdk:16
EXPOSE 8080
#COPY . /app
#WORKDIR /app
ADD target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]
CMD java -jar eureka-server.jar
