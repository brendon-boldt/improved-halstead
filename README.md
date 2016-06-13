# Improved Halstead Metric Parser

This is parser written in Scala which calculates the readability of sample of code as based on an improved set of Halstead metrics as described by [ A Simpler Model of Software Readability ]( http://macbeth.cs.ucdavis.edu/msr2011.pdf ).

To get a readability score for a particular file `scala -cp $CLASSPATH:cgrammar.jar runtime.Main [filename]`

To get readbility scores for all files in a directory `./parse-samples.bash [directory]`
