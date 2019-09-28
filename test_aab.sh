#!/bin/bash

java -jar bundletool-all-0.10.3.jar build-apks --bundle=./app/build/outputs/bundle/debug/app-debug.aab --output=app.apks
rm -rf apks
mkdir apks
unzip app.apks -d apks