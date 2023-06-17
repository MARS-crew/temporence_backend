FROM openjdk:11-jdk as builder

WORKDIR /app

COPY . /app

RUN ./gradlew build

FROM openjdk:11-jdk

WORKDIR /app

COPY --from=builder /app/build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=dev

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]
