#Stage 1
# initialize build and set base image for first stage
FROM maven:3.9.8-eclipse-temurin-22 as stage1
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
# set working directory
WORKDIR /opt/score
# copy just pom.xml
COPY pom.xml .
# go-offline using the pom.xml
RUN mvn dependency:go-offline
# copy your other files
COPY ./src ./src
# compile the source code and package it in a jar file
RUN mvn clean install -Dmaven.test.skip=true
#Stage 2
# set base image for second stage
FROM eclipse-temurin:22.0.1_8-jre-ubi9-minimal
# set deployment directory
WORKDIR /opt/score
# copy over the built artifact from the maven image
COPY --from=stage1 /opt/score/target/ScoreService-0.0.1.jar /opt/score

CMD ["java", "-jar", "ScoreService-0.0.1.jar"]