FROM openjdk:16
EXPOSE 8100
ADD /target/inventory-1.jar inventory.jar
ENTRYPOINT ["java", "-jar", "inventory.jar"]