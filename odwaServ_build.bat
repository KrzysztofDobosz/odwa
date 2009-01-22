#!/bin/bash
set TOMCAT_HOME=c:\eclipse\apache-tomcat-6.0.18
set APPDIR=%cd%

rd /s %TOMCAT_HOME%\webapps\odwaServ
rd %TOMCAT_HOME%\webapps\odwaServ
javac -cp "%TOMCAT_HOME%\lib\servlet-api.jar;%APPDIR%\odwaServlet;%APPDIR%\odwaServlet\out\WEB-INF\lib\mysql-connector-java-5.1.7-bin.jar;%APPDIR%\odwaServlet\out\WEB-INF\lib\odwa.jar" -d "%APPDIR%\odwaServlet\out\WEB-INF\classes" odwaServlet\Odwa.java odwaServlet\BasicDom.java


copy %APPDIR%\opt\metadata.xml %TOMCAT_HOME%\bin

cd odwaServlet\out
7z a odwaServ.zip

copy odwaServ.zip %TOMCAT_HOME%\webapps\odwaServ.war

cd %APPDIR%\odwaServlet\out
del odwaServ.zip

cd %TOMCAT_HOME%\bin
startup.bat