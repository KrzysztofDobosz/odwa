
SOURCES=ODWAClient.java         \
        Gui.java                \
        MetadataProcessing.java \
        DynamicReportLoadAndView.java         \
        QueryExecution.java         \
        ReportSaving.java         \
        StaticReportLoadAndView.java

all: $(SOURCES:.java=.class)

%.class: %.java
	$(JAVAC) $(JFLAGS) -classpath $(JCLASS):../../../.. $<

.PHONY: clean

clean:
	rm -f *.class

