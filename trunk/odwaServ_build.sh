#!/bin/bash

source exports.sh
APPDIR=`dirname $0`;

mkdir out

rm $APPDIR/odwaServlet/out/WEB-INF/lib/odwa.jar

javac -cp "$TOMCAT_HOME/lib/servlet-api.jar:$APPDIR/src/org/pwr/odwa/common:$APPDIR/src/org/pwr/odwa/server:$APPDIR/mysql-connector-java-5.1.7-bin.jar" \
	-d "$APPDIR/out" \
	src/org/pwr/odwa/common/selection/Function.java \
	src/org/pwr/odwa/common/selection/Measure.java \
	src/org/pwr/odwa/common/selection/Method.java \
	src/org/pwr/odwa/common/selection/Path.java \
	src/org/pwr/odwa/common/selection/DimensionEl.java \
	src/org/pwr/odwa/common/selection/DimensionElSet.java \
	src/org/pwr/odwa/common/selection/AxisElement.java \
	src/org/pwr/odwa/common/selection/Axis.java \
	src/org/pwr/odwa/common/selection/SelectionLoader.java \
	src/org/pwr/odwa/common/selection/UserSelection.java \
	src/org/pwr/odwa/common/dbtypes/DBFieldDataType.java \
	src/org/pwr/odwa/common/dbtypes/DBFieldType.java \
	src/org/pwr/odwa/common/result/DBRow.java \
	src/org/pwr/odwa/common/result/DBResult.java \
	src/org/pwr/odwa/common/metadata/MetaID.java \
	src/org/pwr/odwa/common/metadata/MetaElement.java \
	src/org/pwr/odwa/common/metadata/MetaDataView.java \
	src/org/pwr/odwa/common/metadata/MetaDimElement.java \
	src/org/pwr/odwa/common/metadata/MetaDimTable.java \
	src/org/pwr/odwa/common/metadata/MetaDim.java \
	src/org/pwr/odwa/common/metadata/MetaHierarchy.java \
	src/org/pwr/odwa/common/metadata/MetaMeasure.java \
	src/org/pwr/odwa/common/metadata/MetaSlot.java \
	src/org/pwr/odwa/server/structure/DBField.java \
	src/org/pwr/odwa/server/structure/DBTable.java \
	src/org/pwr/odwa/server/structure/DBStructure.java \
	src/org/pwr/odwa/server/engine/SQLQuery.java \
	src/org/pwr/odwa/server/engine/SQLLogicOperator.java \
	src/org/pwr/odwa/server/engine/SQLJoinOperator.java \
	src/org/pwr/odwa/server/engine/MySQLQuery.java \
	src/org/pwr/odwa/server/engine/DBEngine.java \
	src/org/pwr/odwa/server/metadata/UID.java \
	src/org/pwr/odwa/server/metadata/EltType.java \
	src/org/pwr/odwa/server/metadata/Member.java \
	src/org/pwr/odwa/server/metadata/Measure.java \
	src/org/pwr/odwa/server/metadata/DatabaseInfo.java \
	src/org/pwr/odwa/server/metadata/Level.java \
	src/org/pwr/odwa/server/metadata/Dimension.java \
	src/org/pwr/odwa/server/metadata/Hierarchy.java \
	src/org/pwr/odwa/server/metadata/Child.java \
	src/org/pwr/odwa/server/metadata/Meta.java \
	src/org/pwr/odwa/server/metadata/Metadata.java

cp src/org/pwr/odwa/common/selection/* out/org/pwr/odwa/common/selection/
cp src/org/pwr/odwa/common/dbtypes/* out/org/pwr/odwa/common/dbtypes/
cp src/org/pwr/odwa/common/result/* out/org/pwr/odwa/common/result/
cp src/org/pwr/odwa/common/metadata/* out/org/pwr/odwa/common/metadata/
cp src/org/pwr/odwa/server/structure/* out/org/pwr/odwa/server/structure/
cp src/org/pwr/odwa/server/engine/* out/org/pwr/odwa/server/engine/
cp src/org/pwr/odwa/server/metadata/* out/org/pwr/odwa/server/metadata/
cd out

jar cf odwa.jar \
	org/pwr/odwa/common/selection/Function.class \
	org/pwr/odwa/common/selection/Measure.class \
	org/pwr/odwa/common/selection/Method.class \
	org/pwr/odwa/common/selection/Path.class \
	org/pwr/odwa/common/selection/DimensionEl.class \
	org/pwr/odwa/common/selection/DimensionElSet.class \
	org/pwr/odwa/common/selection/AxisElement.class \
	org/pwr/odwa/common/selection/Axis.class \
	org/pwr/odwa/common/selection/SelectionLoader.class \
	org/pwr/odwa/common/selection/UserSelection.class \
	org/pwr/odwa/common/dbtypes/DBFieldDataType.class \
	org/pwr/odwa/common/dbtypes/DBFieldType.class \
	org/pwr/odwa/common/result/DBRow.class \
	org/pwr/odwa/common/result/DBResult.class \
	org/pwr/odwa/common/metadata/MetaID.class \
	org/pwr/odwa/common/metadata/MetaElement.class \
	org/pwr/odwa/common/metadata/MetaDataView.class \
	org/pwr/odwa/common/metadata/MetaDimElement.class \
	org/pwr/odwa/common/metadata/MetaDimTable.class \
	org/pwr/odwa/common/metadata/MetaDim.class \
	org/pwr/odwa/common/metadata/MetaHierarchy.class \
	org/pwr/odwa/common/metadata/MetaMeasure.class \
	org/pwr/odwa/common/metadata/MetaSlot.class \
	org/pwr/odwa/server/structure/DBField.class \
	org/pwr/odwa/server/structure/DBTable.class \
	org/pwr/odwa/server/structure/DBStructure.class \
	org/pwr/odwa/server/engine/SQLQuery.class \
	org/pwr/odwa/server/engine/SQLLogicOperator.class \
	org/pwr/odwa/server/engine/SQLJoinOperator.class \
	org/pwr/odwa/server/engine/MySQLQuery.class \
	org/pwr/odwa/server/engine/DBEngine.class \
	org/pwr/odwa/server/engine/ForeKeyCont.class \
	org/pwr/odwa/server/metadata/UID.class \
	org/pwr/odwa/server/metadata/EltType.class \
	org/pwr/odwa/server/metadata/Member.class \
	org/pwr/odwa/server/metadata/Measure.class \
	org/pwr/odwa/server/metadata/DatabaseInfo.class \
	org/pwr/odwa/server/metadata/Level.class \
	org/pwr/odwa/server/metadata/Dimension.class \
	org/pwr/odwa/server/metadata/Hierarchy.class \
	org/pwr/odwa/server/metadata/Child.class \
	org/pwr/odwa/server/metadata/Meta.class \
	org/pwr/odwa/server/metadata/Metadata.class \
	org/pwr/odwa/common/selection/Function.java \
	org/pwr/odwa/common/selection/Measure.java \
	org/pwr/odwa/common/selection/Method.java \
	org/pwr/odwa/common/selection/Path.java \
	org/pwr/odwa/common/selection/DimensionEl.java \
	org/pwr/odwa/common/selection/DimensionElSet.java \
	org/pwr/odwa/common/selection/AxisElement.java \
	org/pwr/odwa/common/selection/Axis.java \
	org/pwr/odwa/common/selection/SelectionLoader.java \
	org/pwr/odwa/common/selection/UserSelection.java \
	org/pwr/odwa/common/dbtypes/DBFieldDataType.java \
	org/pwr/odwa/common/dbtypes/DBFieldType.java \
	org/pwr/odwa/common/result/DBRow.java \
	org/pwr/odwa/common/result/DBResult.java \
	org/pwr/odwa/common/metadata/MetaID.java \
	org/pwr/odwa/common/metadata/MetaElement.java \
	org/pwr/odwa/common/metadata/MetaDataView.java \
	org/pwr/odwa/common/metadata/MetaDimElement.java \
	org/pwr/odwa/common/metadata/MetaDimTable.java \
	org/pwr/odwa/common/metadata/MetaDim.java \
	org/pwr/odwa/common/metadata/MetaHierarchy.java \
	org/pwr/odwa/common/metadata/MetaMeasure.java \
	org/pwr/odwa/common/metadata/MetaSlot.java \
	org/pwr/odwa/server/structure/DBField.java \
	org/pwr/odwa/server/structure/DBTable.java \
	org/pwr/odwa/server/structure/DBStructure.java \
	org/pwr/odwa/server/engine/SQLQuery.java \
	org/pwr/odwa/server/engine/SQLLogicOperator.java \
	org/pwr/odwa/server/engine/SQLJoinOperator.java \
	org/pwr/odwa/server/engine/MySQLQuery.java \
	org/pwr/odwa/server/engine/DBEngine.java \
	org/pwr/odwa/server/engine/ForeKeyCont.java \
	org/pwr/odwa/server/metadata/UID.java \
	org/pwr/odwa/server/metadata/EltType.java \
	org/pwr/odwa/server/metadata/Member.java \
	org/pwr/odwa/server/metadata/Measure.java \
	org/pwr/odwa/server/metadata/DatabaseInfo.java \
	org/pwr/odwa/server/metadata/Level.java \
	org/pwr/odwa/server/metadata/Dimension.java \
	org/pwr/odwa/server/metadata/Hierarchy.java \
	org/pwr/odwa/server/metadata/Child.java \
	org/pwr/odwa/server/metadata/Meta.java \
	org/pwr/odwa/server/metadata/Metadata.java

cp odwa.jar $APPDIR/odwaServlet/out/WEB-INF/lib/
cd ..
rm -R out

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
