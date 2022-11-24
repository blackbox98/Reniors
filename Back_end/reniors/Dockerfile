FROM openjdk:8 AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod =x ./gradlew
RUN ./gradlew bootJar
#RUN ./gradlew clean build --exclude-task test

FROM openjdk:8
COPY --from=builder build/libs/reniors-0.0.1-SNAPSHOT.jar reniors.jar

EXPOSE 8080
CMD ["java","-jar","/reniors.jar"]