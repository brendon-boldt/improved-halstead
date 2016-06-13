JAR = cgrammar.jar

all: *.scala
	fsc -classpath $$CLASSPATH:$(JAR) *.scala

