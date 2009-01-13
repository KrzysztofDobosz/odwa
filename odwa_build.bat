#!/bin/bash
set GWT_HOME=c:\eclipse\gwt
set GWTEXT_HOME=c:\eclipse\lib
set TOMCAT_HOME=c:\eclipse\apache-tomcat-6.0.18
set APPDIR=%cd%

mkdir out

7z x -osrc\org\pwr\odwa\public js.zip 

java -Xms512m -Xmx512m -cp "%APPDIR%\src;%GWT_HOME%\gwt-user.jar;%GWT_HOME%\gwt-dev-windows.jar;%GWTEXT_HOME%\gwtext.jar" com.google.gwt.dev.GWTCompiler -out "%APPDIR%\out" "$@" org.pwr.odwa.Gui

rd /s src\org\pwr\odwa\public\js
rd src\org\pwr\odwa\public\js

mkdir out\org.pwr.odwa.Gui\WEB-INF\classes
mkdir out\org.pwr.odwa.Gui\WEB-INF\lib

copy web.xml out\org.pwr.odwa.Gui\WEB-INF\web.xml
copy %GWT_HOME%\gwt-servlet.jar out\org.pwr.odwa.Gui\WEB-INF\lib\gwt-servlet.jar
copy mysql-connector-java-5.1.7-bin.jar out\org.pwr.odwa.Gui\WEB-INF\lib\

javac -cp "%APPDIR%\src\org\pwr\odwa\common;%APPDIR%\src\org\pwr\odwa\server;%GWT_HOME%\gwt-user.jar;%GWT_HOME%\gwt-servlet.jar;%APPDIR\mysql-connector-java-5.1.7-bin.jar" -d "%APPDIR%\out\org.pwr.odwa.Gui\WEB-INF\classes" src\org\pwr\odwa\common\selection\Function.java src\org\pwr\odwa\common\selection\Measure.java src\org\pwr\odwa\common\selection\Method.java src\org\pwr\odwa\common\selection\Path.java src\org\pwr\odwa\common\selection\DimensionEl.java src\org\pwr\odwa\common\selection\DimensionElSet.java src\org\pwr\odwa\common\selection\AxisElement.java src\org\pwr\odwa\common\selection\Axis.java src\org\pwr\odwa\common\selection\UserSelection.java src\org\pwr\odwa\common\dbtypes\DBFieldDataType.java src\org\pwr\odwa\common\dbtypes\DBFieldType.java src\org\pwr\odwa\common\result\DBRow.java src\org\pwr\odwa\common\result\DBResult.java src\org\pwr\odwa\common\dbtypes\DBEngineService.java src\org\pwr\odwa\common\dbtypes\DBEngineServiceAsync.java src\org\pwr\odwa\common\metadata\MetaID.java src\org\pwr\odwa\common\metadata\MetaElement.java src\org\pwr\odwa\common\metadata\MetaDataView.java src\org\pwr\odwa\common\metadata\MetaDimElement.java src\org\pwr\odwa\common\metadata\MetaDimTable.java src\org\pwr\odwa\common\metadata\MetaDim.java src\org\pwr\odwa\common\metadata\MetaHierarchy.java src\org\pwr\odwa\common\metadata\MetaMeasure.java src\org\pwr\odwa\common\metadata\MetaSlot.java src\org\pwr\odwa\common\metadata\MetaGUIApiService.java src\org\pwr\odwa\common\metadata\MetaGUIApiServiceAsync.java src\org\pwr\odwa\server\structure\DBField.java src\org\pwr\odwa\server\structure\DBTable.java src\org\pwr\odwa\server\structure\DBStructure.java src\org\pwr\odwa\server\engine\DBEngine.java src\org\pwr\odwa\server\DBEngineServiceImpl.java src\org\pwr\odwa\server\MetaGUIApiServiceImpl.java

cd out\org.pwr.odwa.Gui\
7z a odwa.zip

copy odwa.zip %TOMCAT_HOME%\webapps\odwa.war

cd ..\..

rd /s out
rd out

cd %TOMCAT_HOME%\bin
startup.bat

