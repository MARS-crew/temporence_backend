#!/bin/bash

echo "gradle build -x test"
sudo gradle build -x test
PID=$!
wait $PID

REPOSITORY=/home/ubuntu/temp/ourmindmaze-api
scp -i $HOME/key.pem ./build/libs/ourmindmaze-0.0.1-SNAPSHOT.jar ubuntu@43.200.204.186:$REPOSITORY/build/libs/ &
wait

echo "deploy on dev server"
ssh -i $HOME/key.pem ubuntu@43.200.204.186 "bash -s" < ./run-server.sh &

echo "Done"
sudo gradle clean

