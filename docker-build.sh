#!/bin/bash

mvn clean install

docker build -t acrnm08.azurecr.io/shop:0.0.1 .
docker push acrnm08.azurecr.io/shop:0.0.1

helm upgrade shop application -n shop --install
