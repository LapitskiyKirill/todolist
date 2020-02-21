@echo off 
cd core/build/libs 
java -jar -Dspring.datasource.username=postgres -Dspring.datasource.password=Logmcran1 core.jar 
pause