# eval.jitter

This repository contains an evaluation of the [jitter](https://github.com/mictaege/jitter-plugin) Gradle plugin and demonstrates how to build and distribute different flavours of a simple applpication from a single source base.

## Prerequisites

- In order to run the application a JDK including JavaFX is required. e.g. `sdk install java 11.0.17.fx-librca`.
- On Linux/WSL libgtk-3-0 is required also: `sudo apt install libgtk-3-0`

## Using the application

In this simple application the flavors are different types of space agencies: _ESA_, _NASA_ and _ROSKOSMOS_.

Because of the _jitter_ configuration in the _build.gradle_ file this project has three additional tasks: ```flavourESA```, ```flavourNASA``` and ```flavourROSKOSMOS```. This additional tasks are used to select the flavour which should be build or run.

## Examples

```Shell
gradle flavourNASA clean run
```

Will run the application with the flavour _NASA_.

```Shell
gradle flavourESA clean build
```

Will build the application with the flavour _ESA_.

**Note** If the application was build or run with another flavour before, the next build or run should always include a ```clean```.

To execute all test for all flavours omit the flavour selection:

```Shell
gradle clean test
```

Running the application requires the selection of one flavour.