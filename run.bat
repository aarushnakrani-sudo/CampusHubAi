@echo off
set "JAVA_HOME=C:\Users\vanci\.jdks\openjdk-26.0.1"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo ====================================================
echo               Compiling CampusHubAI...
echo ====================================================
if not exist out\production\CampusHubAi mkdir out\production\CampusHubAi

:: Find all java files and compile them
dir /s /b src\*.java > sources.txt
javac -cp "lib\mysql-connector-j-9.7.0.jar" -d "out\production\CampusHubAi" @sources.txt
del sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation Failed!
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo ====================================================
echo               Running CampusHubAI...
echo ====================================================
java -cp "out\production\CampusHubAi;lib\mysql-connector-j-9.7.0.jar" com.campushub.main.CampusHubAI
pause
