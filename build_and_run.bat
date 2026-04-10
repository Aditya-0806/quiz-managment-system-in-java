@echo off
echo ===== Student Quiz System ===== 
echo Compiling...
mkdir bin 2>nul
javac -cp "lib/*" -d bin -sourcepath src src/com/quiz/Main.java
if %errorlevel% NEQ 0 (
    echo Compilation FAILED. Check errors above.
    pause
    exit /b 1
)
echo Compilation successful!
echo Starting application...
java -cp "bin;lib/*" com.quiz.Main
pause
