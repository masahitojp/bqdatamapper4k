package com.github.masahitojp.bqdatamapper4k

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// use this code. Thanks Tom Hanley
// https://stackoverflow.com/a/56347214

val gson: Gson = Gson()

//convert a data class to a map
fun <T> T.serializeToMap(): Map<String, Any> {
    return convert()
}

//convert a map to a data class
inline fun <reified T> Map<String, Any>.toDataClass(): T {
    return convert()
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}