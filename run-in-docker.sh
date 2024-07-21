#!/usr/bin/env bash

IMAGE_NAME=sstol-hangman

docker build -t $IMAGE_NAME .
docker run -it $IMAGE_NAME