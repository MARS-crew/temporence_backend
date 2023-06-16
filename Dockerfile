FROM openjdk:11-jdk as builder

WORKDIR /app

COPY . /app

RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-7.0.2-bin.zip && \
    unzip gradle-7.0.2-bin.zip && \
    rm gradle-7.0.2-bin.zip

RUN /app/gradle-7.0.2/bin/gradle build

FROM openjdk:11-jre

WORKDIR /app

COPY --from=builder /app/build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=dev

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]
