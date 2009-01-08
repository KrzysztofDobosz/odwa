
SOURCES=Report.java        \
        StaticReport.java  \
        DynamicReport.java

all: $(SOURCES:.java=.class)

%.class: %.java
	$(JAVAC) $(JFLAGS) -classpath $(JCLASS):../../../../.. $<

.PHONY: clean

clean:
	rm -f *.class

