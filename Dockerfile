# Stage 1: Build stage
FROM adoptopenjdk:11-jdk-hotspot as builder

WORKDIR /app

COPY . /app

RUN chmod +x ./gradlew
RUN ./gradlew build

# Stage 2: Runtime stage for ARM
FROM adoptopenjdk:11-jre-hotspot as arm_runtime

WORKDIR /app

COPY --from=builder /app/build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=dev

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]

# Stage 3: Runtime stage for AMD
FROM adoptopenjdk:11-jre-hotspot as amd_runtime

WORKDIR /app

COPY --from=builder /app/build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=dev

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]
