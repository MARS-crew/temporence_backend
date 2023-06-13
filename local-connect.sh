#!/bin/bash

redis-server &

ssh -L 3306:localhost:3306 mars_temporence
