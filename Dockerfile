FROM java:8-jre
WORKDIR C:\\Program Files (x86)\\Jenkins\\workspace\\LoginService_Jenkins\\target
ENV MYSQL_DATABASE=user_data
ENV MYSQL_URL=mysql://my-mysql:3306/user_data
ADD .\\target\\login-service-0.0.1-SNAPSHOT.jar C:\\Program Files (x86)\\Jenkins\\\workspace\LoginService_Jenkins\\docker\\login-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","login-service-0.0.1-SNAPSHOT.jar"]