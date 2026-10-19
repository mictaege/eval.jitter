# eval.jitter

This repository contains an evaluation of the [jitter](https://github.com/mictaege/jitter-plugin) Gradle plugin and demonstrates how to build and distribute different flavours of a simple applpication from a single source base.

## Prerequisites

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

## Flavour-variant compile-time constants

The ```modelTest``` source set demonstrates the flavour handling of compile-time
constants ([```OnlyIfTest```](src/modelTest/java/com/github/mictaege/eval/jitter/OnlyIfTest.java)
and [```OnlyIfStringTest```](src/modelTest/java/com/github/mictaege/eval/jitter/OnlyIfStringTest.java)):
javac inlines constant variables into their usages, so the flavour stripping cannot apply an
```@OnlyIf``` gate at the usage sites. Flavour-variant constants therefore need the
```@InlinedConstantApproved``` marker - the review assertion that every usage sits on a member
gated with a subset of the constant's flavours - and the usages in the demo are own-flavour only,
because a usage in a wider gated member would keep the inlined full model value. The opt-in check
verifies that assertion mechanically:

```Shell
gradle flavourNASA jitterVerifyInlinedConstants
```

## Project structure

The example is a multi module build that shows the source set roles of the _jitter_ plugin:

- The `simulator` subproject is a flavour library. Its `main` source set has the _packaging_ role (the default): the simulator's runtime artifact is its flavour-stripped jar. Its `sharedFixtures` source set has the _api_ role (configured via `jitter.apiSrcSets`): the full classes stay the compile artifact and the stripped `jitterSharedFixturesJar` replaces the outgoing runtime variant, wired like the standard test fixtures and selected by the consumers by capability. The `SimFixtureFlavourTest` of the root project demonstrates the contract: it compiles against the full 150% model of the fixtures and runs against the stripped model of the active flavour.
- The root project's `model`, `beans` and `ui` source sets are test-like (no explicit role): the stripped tree is their runtime identity, downstream source sets compile against the full classes via task references (see the `fullOf` helper in the build script). Its `main` and `test` source sets are excluded from the flavouring: the demo application assembles the flavour models, and the flavour-agnostic test verifies the stripped simulator fixtures reflectively.
- The `DejitteredModelTest` of the root project demonstrates the source delivery: the aggregate `dejitter` task creates the dejittered source variant of every flavour-specific source set under `build/jitter-dejittered/<srcSet>` (`java` and `resources`), and the test asserts the variant's shape for the active flavour - the forked method bodies, the altered classes (including the fully qualified template of the `nasa` package and the in-file template of the ESA flavour), the removed flavour packages, the enum constants and the flavour-renamed resources. The `model` source set also carries a Kotlin source (`KotlinGadgets.kt`): the Kotlin sources dejitter like the Java sources, and their annotation values reference the compile time constants of the `Flavour` class. Without an active flavour the variant is the annotation-cleaned 150% source model.