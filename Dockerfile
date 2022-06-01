FROM openjdk:11
ADD target/pycemaker-0.0.1-SNAPSHOT.jar pycemaker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","pycemaker-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080