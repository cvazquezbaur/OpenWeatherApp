package com.my.weather.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonHandler {

    val gson = Gson()

    inline fun <reified T> encodeToJson(data: T): String {
        return gson.toJson(data)
    }

    inline fun <reified T> decodeFromJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return gson.fromJson(json, type)
    }
}