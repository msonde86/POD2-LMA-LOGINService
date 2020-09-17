FROM openjdk:8
EXPOSE 8081
ADD target/login-service-0.0.1-SNAPSHOT.jar login-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","login-service-0.0.1-SNAPSHOT.jar"]