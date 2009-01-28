#!/bin/bash

source exports.sh
APPDIR=`dirname $0`;

javac -cp "$TOMCAT_HOME/lib/servlet-api.jar:$APPDIR/odwaServlet:$APPDIR/odwaServlet/out/WEB-INF/lib/mysql-connector-java-5.1.7-bin.jar:$APPDIR/odwaServlet/out/WEB-INF/lib/odwa.jar" \
	-d "$APPDIR/odwaServlet/out/WEB-INF/classes" \
	odwaServlet/Odwa.java \
	odwaServlet/MetaXmlParserServlet.java

cp opt/metadata.xml $TOMCAT_HOME/bin/

cd odwaServlet/out/
zip -qr odwaServ *

rm -R $TOMCAT_HOME/webapps/odwaServ
rm $TOMCAT_HOME/webapps/odwaServ.war
cp odwaServ.zip $TOMCAT_HOME/webapps/odwaServ.war

rm odwaServ.zip

cd $TOMCAT_HOME/bin
./startup.sh
read -p "Press enter to shutdown tomcat server..."
./shutdown.sh
