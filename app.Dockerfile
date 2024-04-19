FROM bellsoft/liberica-openjdk-alpine:17 AS build
COPY ./ /srv/app

RUN cd /srv/app && chmod +x mvnw && ./mvnw clean package -DskipTests
FROM bellsoft/liberica-openjdk-alpine:17 AS mhtm
WORKDIR /srv
COPY --from=build /srv/app/target/javacode-0.0.1-SNAPSHOT.war /srv/app/javacode-0.0.1-SNAPSHOT.war
ENTRYPOINT exec java -jar /srv/app/javacode-0.0.1-SNAPSHOT.war

#FROM openjdk:17-jdk
#WORKDIR /app
#COPY target/*.war /app/app.war
#ENTRYPOINT ["java", "-jar", "/app/app.war"]