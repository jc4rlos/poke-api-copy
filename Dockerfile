FROM openjdk:11-jdk-alpine
LABEL maintainer="Carlos"
ENV spring.application.name pokedex
COPY build/libs/pokedex-*SNAPSHOT.jar /opt/pokedex.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8","-jar","/opt/pokedex.jar"]