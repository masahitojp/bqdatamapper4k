# bqdatamapper4k
It's a glue code in BigQuery for Kotlin users.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.masahitojp/bqdatamapper4k/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.github.masahitojp/bqdatamapper4k/badge.svg)

## Getting Started

### Prerequisites

Install OpenJDK v8 later

### Installing

for gradle users
```
repositories {
    mavenCentral()
}
dependencies {
    implementation( "com.github.masahitojp:bqdatamapper4k:0.1.0")
}
```

### How to use

*[TableRow](https://developers.google.com/resources/api-libraries/documentation/bigquery/v2/java/latest/com/google/api/services/bigquery/model/TableRow.html?is-external=true)*

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