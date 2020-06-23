.PHONY: clean compile

compile : build/Hello.class bin/c_hello

clean :
	rm -rf build
	rm -rf bin
	rm -rf test_bin
	mkdir -p build
	mkdir -p bin
	mkdir -p test_bin

bin/c_hello :
	gcc src/hello_world.c -o bin/c_hello

build/Hello.class :
	javac src/Hello.java -d build

test_bin/dll_harness :
	gcc test/c_harness/dll_harness.c src/practice/double_linked_list.c -o test_bin/dll_harness
