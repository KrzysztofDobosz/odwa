
SOURCES=MetaID.java         \
        MetaElement.java    \
        MetaSlot.java       \
        MetaDataView.java   \
        MetaDimTable.java   \
        MetaDim.java        \
        MetaDimElement.java \
        MetaHierarchy.java  \
        MetaMeasure.java    \
        MetaGUIApi.java

all: $(SOURCES:.java=.class)

%.class: %.java
	$(JAVAC) $(JFLAGS) -classpath $(JCLASS):../../../.. $<

.PHONY: clean

clean:
	rm -f *.class

