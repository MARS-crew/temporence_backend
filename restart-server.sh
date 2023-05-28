#!/bin/bash

# Spring Boot 애플리케이션의 JAR 파일명
JAR_FILE="ourmindmaze-0.0.1-SNAPSHOT.jar"

# 실행 중인 Spring Boot 애플리케이션 프로세스 종료
function stop_spring_boot {
    echo "Stopping Spring Boot application..."
    PID=$(ps -ef | grep "$JAR_FILE" | grep -v grep | awk '{print $2}')
    if [[ -n $PID ]]; then
        kill $PID
        echo "Spring Boot application stopped."
    else
        echo "No running Spring Boot application found."
    fi
}

# Spring Boot 애플리케이션 실행
function start_spring_boot {
    echo "Starting Spring Boot application..."
    nohup java -jar "$JAR_FILE" >/dev/null 2>&1 &
    PID=$!
    echo "Spring Boot application started with process ID: $PID"
}

# 스크립트 실행 시 Spring Boot 애플리케이션 종료 후 재시작
stop_spring_boot
start_spring_boot
