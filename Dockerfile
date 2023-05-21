FROM adoptopenjdk:11-jdk-hotspot-bionic

ENV APP_HOME=/apps

ARG JAR_FILE_PATH=build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar

WORKDIR $APP_HOME

COPY $JAR_FILE_PATH app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# docker build --platform linux/arm64/v8 --build-arg DEPENDENCY=build/dependency --no-cache --rm -f Dockerfile -t pinomaker/mars-game:latest .

# docker push pinomaker/mars-game:latest
