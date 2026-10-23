# AGENTS.md — eval.jitter

## Project purpose
`eval.jitter` is an evaluation/demo application for the
[`jitter-plugin`](https://github.com/mictaege/jitter-plugin) Gradle plugin. It demonstrates how to
build and distribute different "flavours" of a simple JavaFX application from a single source base.
The flavours modeled here are different space agencies — _ESA_, _NASA_, and _ROSKOSMOS_ — selected
via generated Gradle tasks (`flavourESA`, `flavourNASA`, `flavourROSKOSMOS`).

Part of the jitter family: this is the **reference example/consumer** of `jitter-plugin`
(which in turn depends on `jitter-api`). Its purpose is illustrative and demonstrational rather
than being a library or reusable component.

## Technical stack
- Language: Java, split across several custom source sets: `src/main`, `src/model`, `src/beans`,
  `src/ui` (plus matching `*Test` source sets for each) — not a plain `main`/`test` layout.
- Build tool: Gradle (Groovy DSL, `build.gradle`, not Kotlin DSL like most sibling projects) —
  build via `./gradlew`.
- UI framework: JavaFX (`org.openjfx.javafxplugin`), using `javafx.controls` and `javafx.fxml`
  modules.
- Key buildscript dependency: `jitter-plugin`, applied via `apply plugin` — resolved through the
  composite build of the sibling checkouts (`includeBuild("../jitter-api")`,
  `includeBuild("../jitter-plugin")` in `settings.gradle`), so local plugin changes are picked up
  without publishing.
- Testing: JUnit Jupiter (JUnit BOM-managed).
- Runtime prerequisite: on Linux/WSL, `libgtk-3-0` must be installed (`sudo apt install libgtk-3-0`)
  for the JavaFX UI to run.
- License: Apache License 2.0. Not published to Maven Central (it's a demo app, not a library).

## Project semantics / domain notes
- Flavours can be switched without a `clean`: the active flavour is a task input of the strip,
  verify, resource and jar tasks (bytecode stripping replaced the former source transformation).
- The custom source sets (`model`, `beans`, `ui`) reflect an MVVM/MVC-like separation; each compiles
  as the complete 150% model and is flavour-stripped independently.
- The demo covers the plugin's full feature set: `@OnlyIf`/`@Fork`/`@Alter`/`@AlterConstValue`,
  flavour-variant inlined constants, flavour-specific resources and the XML resource gates
  (`Telemetry.xsd`), and the dejittered source variant (`dejitter` tasks) for source deliveries.

## Working conventions
- Treat this project as a living example: prefer changes that keep the flavour demo simple and easy
  to follow over adding unrelated application complexity.
- After changing `jitter-plugin` or `jitter-api`, validate the change by building/running this
  project with each flavour.
