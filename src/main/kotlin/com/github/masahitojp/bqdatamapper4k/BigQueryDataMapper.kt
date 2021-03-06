/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.masahitojp.bqdatamapper4k

import com.google.api.services.bigquery.model.TableRow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * convert BigQuery TableRow to Data classes
 *
 * @author mashitojp<randomstep@gmail.com>
 * @since 0.1.0
 */
inline fun <reified T> TableRow.toDataClass(): T {
    return this.toMap().toDataClass()
}

/**
 * convert JSON String to BigQuery TableRow
 *
 * @author mashitojp<randomstep@gmail.com>
 * @since 0.2.0
 */
fun String.toTableRow(
    now: LocalDateTime = LocalDateTime.now(),
    formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss")
): TableRow {
    val outputRow = gson.fromJson(this, TableRow::class.java)
    if (outputRow.containsKey("timestamp")) {
        outputRow["timestamp"] = formatter.format(now)
    }
    return outputRow
}