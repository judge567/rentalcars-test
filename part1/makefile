JFLAGS = -g -cp '.:json-simple-1.1.jar'
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	part1.java \
	Vehicle.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
