# Nasa Dao

### [Examples](- "shouldProvideNasaBearers")

Given the flavour _NASA_

When the bearers asked

Then it should provide the following bearer:


| [search][] [Bearer][match]   |
| ------------------------------ |
| Atlas                          |
| Titan                          |

[search]: - "c:verify-rows=#result:shouldProvideNasaBearers()"
[match]: - "?=#result.name"