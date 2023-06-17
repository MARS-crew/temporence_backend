FROM adoptopenjdk:11-jdk-hotspot@sha256:cb0aa79a8aa4 as builder

WORKDIR /app

COPY . /app

RUN ./gradlew build

FROM adoptopenjdk:11-jre-hotspot@sha256:cb0aa79a8aa4

WORKDIR /app

COPY --from=builder /app/build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=dev

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]
