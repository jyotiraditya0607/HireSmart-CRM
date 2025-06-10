#!/bin/bash

# Create output directory if it doesn't exist
mkdir -p out

# Compile the Java files
javac -cp ".:ojdbc8.jar" -d out src/com/hiresmart/*.java src/com/hiresmart/util/*.java src/com/hiresmart/config/*.java

# Run the program
java -cp ".:ojdbc8.jar:out" com.hiresmart.TestConnection