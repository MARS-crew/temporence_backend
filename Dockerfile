FROM adoptopenjdk:11-jdk-hotspot@sha256:cb0aa79a8aa432dbfae4e2e13ac4a67b267c5e3e0f70b58c6e8b78b6f1d01ebe as builder

WORKDIR /app

COPY . /app

RUN ./gradlew build

FROM adoptopenjdk:11-jre-hotspot@sha256:cb0aa79a8aa432dbfae4e2e13ac4a67b267c5e3e0f70b58c6e8b78b6f1d01ebe

WORKDIR /app

COPY --from=builder /app/build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=dev

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]

