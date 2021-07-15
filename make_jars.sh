#!/bin/bash

make_jars () {
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

delete_jars() {
	services=("eureka-server" "config" "Spring-Cloud-Gateway" "accounts" "inventory" "commerce")

	for ((i=0; i<${#services[@]}; i++)); do
		ls ${services[i]}/target
		rm -rf ${services[i]}/target
	done

}


if [ $1 = "jars" ] ; then
	make_jars
elif [ $1 = "del" ] ; then
	delete_jars
else
	echo unknown command
	exit 1
fi
