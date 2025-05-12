#!/bin/bash
hostname=$(curl http://169.254.169.254/metadata/v1/hostname)
docker run -d -p 8090:8090 --name nutricenter-"$hostname" augustomarco/nutricenter:"$hostname"
