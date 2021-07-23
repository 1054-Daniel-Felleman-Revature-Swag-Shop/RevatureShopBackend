#!/bin/bash


sudo add-apt-repository ppa:linuxuprising/java

sudo apt update

sudo apt install oracle-java16-installer

sudo apt install oracle-java16-installer --no-install-recommends

java --version
