/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.masahitojp.bqdatamapper4k

import com.google.api.services.bigquery.model.TableRow
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test
    fun testFoo() {
        val row: TableRow = TableRow().set("aaa", 2)
        val results: Foo = row.toDataClass<Foo>()
        assertEquals(results, Foo(2))
    }

    @Test
    fun testBar() {
        val row = TableRow().set("a", 2)
        val results = row.toDataClass<Bar>()
        assertEquals(results, Bar(2, null, null))
    }

    @Test
    fun testDataclassWithDefaultValue() {
        val row = TableRow().set("a", 2)
        val results = row.toDataClass<Baz>()
        // we should check null
        assertEquals(results, Baz(2, null, null))
    }

    @Test
    fun testDataclassWithListParams() {
        val row = TableRow()
            .set("a", 2)
            .set("b", listOf(1, 2))
        val results = row.toDataClass<Hoge>()
        assertEquals(results, Hoge(2, listOf<Int>(1, 2)))

        assertEquals(
            results.serializeToMap(),
            mapOf("a" to 2.0, "b" to listOf(1.0, 2.0))
        )
    }

    @Test
    fun toJson() {
        val row = TableRow()
            .set("a", 2.0)
        val results = toTableRow(
            """
            { "a" : 2 }
        """.trimIndent()
        )
        assertEquals(results, row)
    }
}
