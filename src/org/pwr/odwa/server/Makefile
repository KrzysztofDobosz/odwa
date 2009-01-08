
SOURCES=MetaSQLApi.java \
        MetaGUIApiImpl.java \
        InternalMetadataReceival.java \
        ODWAServer.java

all: $(SOURCES:.java=.class)

%.class: %.java
	$(JAVAC) $(JFLAGS) -classpath $(JCLASS):../../../.. $<

.PHONY: clean

clean:
	rm -f *.class

