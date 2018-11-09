#! /bin/bash

echo "Param 1: $1"

#local_ip=$(ifconfig eth0 | awk '/inet addr:/ {print $2}'|sed 's/addr://')
local_ip=$(hostname -i)

echo "local_ip $local_ip"

java -jar target/$1 -cluster $local_ip