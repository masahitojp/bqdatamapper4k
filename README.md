# bqdatamapper4k
It's a glue code in BigQuery for Kotlin users.

## Getting Started

### Prerequisites

Install OpenJDK v8 later

### Installing

TBD

### How to use

*TableRow*

```kotlin
data class Foo(val aaa:Long)

val row: TableRow = TableRow().set("aaa", 2)
val results: Foo = row.toDataClass<Foo>()
assertEquals(results, Foo(2))

```

## Running the tests

```
./gradlew test
```

## Authors

masahitojp

## License

Apache License, Version 2.0