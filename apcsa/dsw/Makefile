run: build execute

execute: 
	java -cp ./bin com/Main

build:
	find -name "*.java" > sources.txt
	tac sources.txt > sources_rev.txt
	javac -d ./bin @sources_rev.txt
clean:
	rm -rf ./bin/*
	rm sources.txt

#jar: build
