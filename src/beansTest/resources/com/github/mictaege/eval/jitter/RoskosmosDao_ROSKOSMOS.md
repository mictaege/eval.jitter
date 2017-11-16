# Roskosmos Dao

### [Examples](- "shouldProvideRoskosmosBearers")

Given the flavour _ROSKOSMOS_

When the bearers asked

Then it should provide the following bearer:


| [search][] [Bearer][match]   |
| ------------------------------ |
| Wostok                         |
| Sojus                          |

[search]: - "c:verify-rows=#result:shouldProvideRoskosmosBearers()"
[match]: - "?=#result.name"