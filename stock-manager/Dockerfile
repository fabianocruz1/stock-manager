FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} stock-manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/stock-manager-0.0.1-SNAPSHOT.jar"]