FROM openjdk:8
EXPOSE 8080
#COPY . /app
#WORKDIR /app
ADD target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]
CMD /usr/lib/jvm/jdk-16.0.1/bin/java -jar eureka-server.jar
