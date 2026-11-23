set -a
[ -f .env ] && source .env
set +a

#!/usr/bin/env bash

set -e

# Move to the project root
cd "$(dirname "$0")"

# Create output directory
mkdir -p out

echo "Compiling Java application..."

# Compile all Java source files, including subpackages
find src/main/java -name "*.java" -print0 | \
    xargs -0 javac --add-modules jdk.httpserver -d out

echo "Compilation successful!"

echo "Starting Authentication Service..."

# Run application
java --add-modules jdk.httpserver \
    -cp out \
    com.de_residency.auth.Main
