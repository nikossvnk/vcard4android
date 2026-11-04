
[![build status](https://github.com/nikossvnk/vcard4android/actions/workflows/check.yml/badge.svg)](https://github.com/nikossvnk/vcard4android/actions/workflows/check.yml)


# vcard4android

vcard4android is an Android library that brings together VCard and Android.
It's a framework for

* parsing and generating VCard resources (using [ez-vcard](https://github.com/mangstadt/ez-vcard))
  from/into data classes that are compatible with the Android Contacts Provider, and
* accessing the Android Contacts Provider by a unified API.

It has been primarily developed for [DAVx⁵](https://www.davx5.com).

_This software is not affiliated to, nor has it been authorized, sponsored or otherwise approved
by Google LLC. Android is a trademark of Google LLC._

Discussion: https://forums.bitfire.at/category/18/libraries


## Development and API migration

This fork is maintained at `github.com/nikossvnk/vcard4android`. Use JDK 25,
the included Gradle 9.8.2 wrapper, and Android SDK Platform 37.0. The module uses
AGP 9.5.0, built-in Kotlin 2.4.21, Java 17 bytecode, minSdk 23, and core-library
desugaring.

```sh
./gradlew build compileDebugAndroidTestKotlin
./gradlew connectedCheck
```

ez-vcard 0.12.2 represents full dates with `Temporal`, not `java.util.Date`.
Use `Contact.toLocalDate(temporal)` where a local date is needed; partial dates
remain `PartialDate`. Preserve missing-year birthdays and leap-day semantics.
Consumer R8 rules cover ez-vcard's optional reflective integrations.

JUnit4 JVM tests cover vCard parsing/serialization and date conversion.
Instrumented provider tests need a device/emulator and contacts permissions.
CI builds, lints, runs JVM tests, and compiles instrumentation; it does not execute
device tests. Dependabot and scheduled CI run weekly.

## Contact

```
bitfire web engineering – Stockmann, Hirner GesnbR
Florastraße 27
2540 Bad Vöslau, AUSTRIA
```

Email: [play@bitfire.at](mailto:play@bitfire.at) (do not use this)


## License 

Copyright (C) bitfire web engineering (Ricki Hirner, Bernhard Stockmann).

This program comes with ABSOLUTELY NO WARRANTY. This is free software, and you are welcome
to redistribute it under the conditions of the [GNU GPL v3](https://www.gnu.org/licenses/gpl-3.0.html).

