#!/bin/bash

mkdir logs

services=("eureka-server" "config" "Spring-Cloud-Gateway" "accounts" "inventory" "commerce")
ports=(9000 8888 9001 8000 8100 8200)

for ((i = 0; i < 6; i++)); do
  service=${services[i]}
  port=${ports[i]}

  echo "Starting $service"

  export BUILD_ID="$service"
  nohup java -jar "$service/target/$service-1.jar" >"logs/$service.log" &

  until $(curl --output /dev/null --silent --head "http://localhost:$port"); do
    printf '.'
    sleep 2
  done
done

exit 0
