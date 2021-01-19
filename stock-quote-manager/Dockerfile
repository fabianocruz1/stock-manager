FROM openjdk:8-jdk-alpine

#  RUN 
# Update
# apt-get update -y && \
# Install Java
# apt-get install default-jre -y

ADD ./target/*.jar stock-quote.jar

EXPOSE 8081

CMD java -jar stock-quote.jar

