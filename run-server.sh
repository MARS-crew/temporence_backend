#!/bin/bash
REPOSITORY=/home/ubuntu/temp/ourmindmaze-api

echo "cd ${REPOSITORY}"
cd $REPOSITORY

V_PID=$(ps -ef | grep 'jar' | awk '{print $2}')

if test -z "$V_PID"
then
      echo "\$V_PID is empty"
else
      echo "ill $V_PID"
      sudo kill -9 $V_PID
      wait
fi

FILE=$REPOSITORY/ourmindmaze-0.0.1-SNAPSHOT.jar
if [ -f "$FILE" ]; then
    echo "$FILE exists."
    rm -rf [ourmindmaze-0.0.1-SNAPSHOT.jar
fi
wait
echo "cp -f ${REPOSITORY}/build/libs/*.jar ${REPOSITORY}/"
cp -f $REPOSITORY/build/libs/*.jar $REPOSITORY/
wait

echo "cd ${REPOSITORY}"
cd $REPOSITORY


echo "start java"
nohup java -jar -Duser.timezone=Asia/Seoul -Dspring.profiles.active=dev ourmindmaze-0.0.1-SNAPSHOT.jar &
