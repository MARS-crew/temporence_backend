#!/bin/bash

ssh -L 3306:localhost:3306 -L 6379:localhost:6379 mars_temporence
