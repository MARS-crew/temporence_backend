# Stage 1: Build stage
FROM adoptopenjdk:11-jdk-hotspot as builder

WORKDIR /app

COPY . /app

RUN ./gradlew build

# Stage 2: Runtime stage
FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY --from=builder /app/build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=dev

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]
