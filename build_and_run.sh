#!/bin/bash
echo "===== Student Quiz System ====="
mkdir -p bin
echo "Compiling..."
javac -cp "lib/*" -d bin -sourcepath src src/com/quiz/Main.java
if [ $? -ne 0 ]; then
  echo "Compilation FAILED."
  exit 1
fi
echo "Compilation successful!"
echo "Starting application..."
java -cp "bin:lib/*" com.quiz.Main
