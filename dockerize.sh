#!/bin/bash

# convenience script for creating/deleting docker files after compiling services

make_images () {
services=("eureka-server" "config" "Spring-Cloud-Gateway" "accounts" "inventory" "commerce")

for ((i=0; i<${#services[@]}; i++)); do
	cd ${services[i]}
	#mvn clean package
	if [ ${services[i]} = "Spring-Cloud-Gateway" ]; then
		echo 1
		docker build -t 'aypas/spring-cloud-gateway' .
	else
		echo 2
		docker build -t aypas/${services[$i]} .
	fi
	echo `ls`
	cd ..
done
}

delete_images() {
	services=("eureka-server" "config" "Spring-Cloud-Gateway" "accounts" "inventory" "commerce")

	for ((i=0; i<${#services[@]}; i++)); do
		ls ${services[i]}/target
		rm -rf ${services[i]}/target
	done

}


if [ $1 = "make" ] ; then
	make_images
elif [ $1 = "del" ] ; then
	delete_images
else
	echo unknown command
	exit 1
fi
