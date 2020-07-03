.PHONY: clean compile generate

compile : build/Hello.class bin/c_hello bin/jlox.jar

generate : bin/generate_ast.jar
	java -jar bin/generate_ast.jar src/com/craftinginterpreters/lox

run : bin/jlox.jar
	java -jar bin/jlox.jar

clean :
	rm -rf build
	rm -rf bin
	rm -rf test_bin
	mkdir -p build
	mkdir -p bin
	mkdir -p test_bin

bin/c_hello :
	gcc src/hello_world.c -o bin/c_hello

bin/jlox.jar :
	javac src/com/craftinginterpreters/lox/*.java -d build
	cd build && jar cfev jlox.jar com.craftinginterpreters.lox.Lox com
	mv build/jlox.jar bin/jlox.jar

bin/generate_ast.jar :
	rm -rf build
	mkdir build
	javac src/com/craftinginterpreters/tool/GenerateAst.java -d build
	cd build && jar cfev generate_ast.jar com.craftinginterpreters.tool.GenerateAst com
	mv build/generate_ast.jar bin/generate_ast.jar

build/Hello.class :
	javac src/Hello.java -d build

test_bin/dll_harness :
	gcc test/c_harness/dll_harness.c src/practice/double_linked_list.c -o test_bin/dll_harness
