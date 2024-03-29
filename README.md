# modular-synth

A program in which you can build synthesizers from scratch using a node graph editor. This was done as a project for the course "Programming studio A" at Aalto University. Written in scala 3, but makes use of the java sound and javafx libraries. 

## Getting started

As the program is built in scala, running it will need a working installation of java. An executable .jar is provided under "out/artifacts/modular_synth.jar/modular_synth.jar". If, for whatever reason, this does not work, you can build the program using [sbt](https://www.scala-sbt.org/). Having sbt installed, navigate to the project's root directory and run: 

    sbt compile run

The progam should compile and start. (This has been tested with OpenJDK 17. JavaFX is also required).

If you want to quickly view the capabilities of the program: From the top bar, select Load synth. Choose a file under "DemoSynths". I recommend "TunableKarplus".

## Features

This program provides a node graph editor, where different "synthesizer components" can be wired together. It provides a simplistic library consisting of basic components, such as oscillators and envelopes. Components have input and output signals of certain data types. A component performs some transformation to its inputs, giving the result as the output. 
The synthesizer can be made to respond to MIDI input. Synths can be saved to files, and loaded later.

For a more detailed technical spec, as well as a primitive use guide, see [OS2_Final_Document.pdf](https://github.com/f87-56/modular-synth/blob/master/OS2_Final_Document.pdf). 
