/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.masahitojp.bqdatamapper4k

import com.google.api.services.bigquery.model.TableRow
import javax.swing.text.TableView
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

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

}
