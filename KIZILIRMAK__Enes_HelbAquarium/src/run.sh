#!/bin/bash 

javac *.java
java HelbAquarium
find . -type f -name "*.class" -delete
