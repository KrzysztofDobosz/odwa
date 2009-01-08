
SOURCES=Method.java         \
        Path.java           \
        Function.java       \
        Measure.java        \
        DimentionEl.java    \
        DimentionElSet.java \
        AxisElement.java    \
        Axis.java           \
        UserSelection.java

all: $(SOURCES:.java=.class)

%.class: %.java
	$(JAVAC) $(JFLAGS) -classpath $(JCLASS):../../../../.. $<

.PHONY: clean

clean:
	rm -f *.class

