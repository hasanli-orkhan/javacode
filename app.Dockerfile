FROM openjdk:17-jdk
WORKDIR /app
COPY target/*.war /app/app.war
ENTRYPOINT ["java", "-jar", "/app/app.war"]