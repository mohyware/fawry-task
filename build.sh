#!/bin/sh

echo "=== Building E-Commerce System ==="

# Create build directory
mkdir -p build

# Compile all Java files
echo "Compiling Java files..."
javac -d build src/main/java/com/fawry/ecommerce/*.java src/main/java/com/fawry/ecommerce/*/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "=== Running Main Demo ==="
    java -cp build com.fawry.ecommerce.Main
else
    echo "Compilation failed!"
    exit 1
fi 