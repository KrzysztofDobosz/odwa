
CLASSPATH="."

PREFIX=org/pwr/odwa/server/metadata

SOURCES=$(PREFIX)/UID.java            \
        $(PREFIX)/EltType.java        \
        $(PREFIX)/Meta.java           \
        $(PREFIX)/Measure.java        \
        $(PREFIX)/Dimension.java      \
        $(PREFIX)/Hierarchy.java      \
        $(PREFIX)/Level.java          \
        $(PREFIX)/Member.java         \
        $(PREFIX)/Metadata.java       \
        $(PREFIX)/DatabaseInfo.java

all: $(SOURCES:.java=.class)

$(PREFIX)/%.class: $(PREFIX)/%.java
	javac -cp $(CLASSPATH) $<

.PHONY: clean run

clean:
	rm -f $(PREFIX)/*.class

run:
	@java -cp $(CLASSPATH) $(PREFIX)/Metadata

