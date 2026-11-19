# AGENTS.md — eval.jitter

> New to the jitter family? Read the onboarding guide in the family store first:
> https://github.com/mictaege/jitter-openspec (README.md).

Project context lives in OpenSpec: `openspec/config.yaml`. This project is the reference
demo/consumer of the jitter family — behavior specs live in the sibling repos
(`jitter-api/openspec/specs/annotations`, `jitter-plugin/openspec/specs/*`, `jitter/openspec/specs/gate-schema`),
family-level planning in the store `jitter` (`~/openspec/jitter`, use `openspec ... --store jitter`).

## Working conventions

- Treat this project as a living example: prefer changes that keep the flavour demo simple and
  easy to follow over adding unrelated application complexity.
- After changing `jitter-plugin` or `jitter-api`, validate the change by building/running this
  project with each flavour.
- `settings.gradle` composite-builds `../jitter-api` and `../jitter-plugin` via `includeBuild`,
  so local changes there are exercised here without publishing. Build via `./gradlew`
  (Groovy DSL `build.gradle`).
