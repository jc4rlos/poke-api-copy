FROM openjdk:8-jdk-alpine
LABEL maintainer="Carlos"
VOLUME /tmp
EXPOSE 9091
COPY build/libs/pokedex-*SNAPSHOT.jar /opt/pokedex.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8","-jar","/opt/pokedex.jar"]