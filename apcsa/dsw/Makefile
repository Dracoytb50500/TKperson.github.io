run: build execute

execute: 
	java -cp ./bin com/Main

build:
	find -name "*.java" > sources.txt
	javac -d bin @sources.txt
clean:
	rm -rf ./bin/*
	rm sources.txt

#jar: build
