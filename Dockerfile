FROM openjdk:11-jre-slim

COPY target/linkconverter-1.0.0.jar .

ENTRYPOINT ["java","-jar","linkconverter-1.0.0.jar"]