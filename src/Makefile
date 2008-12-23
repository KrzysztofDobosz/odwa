
PREFIX=org/pwr/odwa

DIRS=$(PREFIX)/common \
     $(PREFIX)/server \
     $(PREFIX)/client

JAVA=java
JAVAC=javac

JFLAGS=-Xlint:unchecked

LIBPREFIX=/usr/share

CLASSES=.
CLASSES+=:$(LIBPREFIX)/xmlrpc-3/lib/xmlrpc-common.jar
CLASSES+=:$(LIBPREFIX)/xmlrpc-3/lib/xmlrpc-server.jar
CLASSES+=:$(LIBPREFIX)/xmlrpc-3/lib/xmlrpc-client.jar
CLASSES+=:$(LIBPREFIX)/commons-codec/lib/commons-codec.jar
CLASSES+=:$(LIBPREFIX)/commons-logging/lib/commons-logging.jar
CLASSES+=:$(LIBPREFIX)/ws-commons-util/lib/ws-commons-util.jar

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

run-client:
	@$(JAVA) -classpath $(JCLASS) $(PREFIX)/client/ODWAClient; true

