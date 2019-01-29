#!/bin/sh

./gradlew clean build
java -jar build/libs/dijkstras-0.0.1.jar
