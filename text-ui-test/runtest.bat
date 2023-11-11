@ECHO OFF

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist .\data rd /s /q .\data

REM compile the code into the bin folder
cmd /c "cd .. && .\gradlew.bat clean shadowJar"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar ..\build\libs\duke.jar cli < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
