#!/bin/bash

echo "gradle build -x test"
sudo gradle build -x test
PID=$!
wait $PID

REPOSITORY=/home/ubuntu/temp/ourmindmaze-api
scp ./build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar ubuntu@mars_temporence:$REPOSITORY/build/libs/ &
wait


echo "deploy on dev server"
ssh mars_temporence "bash -s" < ./run-server.sh &

echo "Done"
sudo gradle clean

