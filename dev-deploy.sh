#!/bin/bash

#build and copy to server
echo "gradle build -x test"
gradle build -x test
PID=$!
wait $PID
# 1
REPOSITORY=/home/ubuntu/temp/ourmindmaze-api
scp ./build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar ubuntu@mars_temporence:$REPOSITORY/build/libs/ &
wait


echo "deploy on dev server"
ssh mars_temporence "bash -s" < ./run-server.sh &

echo "Done"

