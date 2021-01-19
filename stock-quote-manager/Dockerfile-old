FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} stock-quote-manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/stock-quote-manager-0.0.1-SNAPSHOT.jar"]