
PREFIX=org/pwr/odwa

DIRS=$(PREFIX)/common/selection \
     $(PREFIX)/common/dbtypes \
     $(PREFIX)/common/result \
     $(PREFIX)/common/metadata \
     $(PREFIX)/client/visualization \
     $(PREFIX)/client/reports \
     $(PREFIX)/client \
     $(PREFIX)/server/structure \
     $(PREFIX)/server/engine \
     $(PREFIX)/server \
     

JAVA=java
JAVAC=javac

JFLAGS=-Xlint:unchecked

LIBPREFIX=/usr/share/java

CLASSES=.
CLASSES+=:$(LIBPREFIX)/xmlrpc-common-3.1.jar
CLASSES+=:$(LIBPREFIX)/xmlrpc-server-3.1.jar
CLASSES+=:$(LIBPREFIX)/xmlrpc-client-3.1.jar
CLASSES+=:$(LIBPREFIX)/commons-codec.jar
CLASSES+=:$(LIBPREFIX)/commons-logging.jar
CLASSES+=:$(LIBPREFIX)/ws-commons-util.jar

JCLASS=$(shell echo $(CLASSES) | sed 's/ :/:/g')

MAKE=make

MFLAGS='JAVA=$(JAVA)'     \
       'JAVAC=$(JAVAC)'   \
       'JFLAGS=$(JFLAGS)' \
       'JCLASS=$(JCLASS)'

all:
	@for i in $(DIRS); do                  \
		(cd $$i; $(MAKE) $(MFLAGS) all);   \
	done

clean:
	@for i in $(DIRS); do                  \
		(cd $$i; $(MAKE) $(MFLAGS) clean); \
	done

.PHONY: run-server run-client

run-server:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/server/ODWAServer; true

internal-metadata-receival:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/server/InternalMetadataReceival; true

run-client:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/client/ODWAClient; true

metadata-processing:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/client/MetadataProcessing; true

query-execution:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/client/QueryExecution; true

report-saving:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/client/ReportSaving; true

static-report-load:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/client/StaticReportLoadAndView; true

dynamic-report-load:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/client/DynamicReportLoadAndView; true
