#!/bin/sh
mvn clean package -Pnative
docker build -f src/main/docker/Dockerfile.native -t cloud-native/bookstore .