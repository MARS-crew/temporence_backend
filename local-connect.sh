#!/bin/bash

ssh -L 3306:localhost:3307 -L 6379:localhost:6379 pinomaker