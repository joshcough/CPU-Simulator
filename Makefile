hello.o: src/experimental/main/cpp/hello.cpp
	g++ src/experimental/main/cpp/hello.cpp -o target/hello.o
	g++ src/experimental/main/cpp/Wire.cpp -o target/wire.o
	g++ src/experimental/main/cpp/vector.cpp -o target/vector.o
	g++ src/experimental/main/cpp/State.cpp src/main/cpp/PowerSource.cpp -o target/electric.o
