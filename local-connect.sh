#!/bin/bash

ssh -L 3306:172.19.0.3:3306 -L 6379:172.19.0.2:6379 mars_temporence