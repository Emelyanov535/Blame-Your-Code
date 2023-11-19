FROM openjdk:17
COPY build/libs/demo-0.0.1-SNAPSHOT-uber.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
