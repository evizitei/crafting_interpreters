.PHONY: clean compile

compile : build/Hello.class bin/c_hello

clean :
	rm -rf build
	rm -rf bin
	mkdir -p build
	mkdir -p bin

bin/c_hello :
	gcc src/hello_world.c -o bin/c_hello

build/Hello.class :
	javac src/Hello.java -d build
