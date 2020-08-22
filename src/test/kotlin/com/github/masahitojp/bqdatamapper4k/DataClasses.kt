package com.github.masahitojp.bqdatamapper4k

data class Foo(val aaa: Long)

data class Bar(
    val a: Long,
    val b: String?,
    val c: Int?
)

data class Baz(
    val a: Long,
    val b: String? = "",
    val c: Int? = -1
)

data class Hoge(
    val a: Int,
    val b: List<Int>,
)
