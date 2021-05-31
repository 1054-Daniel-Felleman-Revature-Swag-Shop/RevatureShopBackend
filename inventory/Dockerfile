FROM openjdk:8
EXPOSE 8080
COPY . /app
WORKDIR /app
ADD target/inventory-1.jar inventory.jar
ENTRYPOINT ["java", "-jar", "/inventory.jar"]
CMD java -jar inventory.jar
