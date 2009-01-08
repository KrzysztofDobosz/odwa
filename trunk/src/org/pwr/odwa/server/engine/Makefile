
SOURCES=DBEngine.java \
        DBEngineApiImpl.java

all: $(SOURCES:.java=.class)

%.class: %.java
	$(JAVAC) $(JFLAGS) -classpath $(JCLASS):../../../../.. $<

.PHONY: clean

clean:
	rm -f *.class

