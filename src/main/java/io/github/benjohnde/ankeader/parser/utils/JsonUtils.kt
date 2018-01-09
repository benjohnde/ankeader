package io.github.benjohnde.ankeader.parser.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type

object JsonUtils {
    fun getMapFromJson(json: String): Map<String, String>? {
        val type = object : TypeToken<Map<String, String>>() {

        }.type
        return Gson().fromJson<Map<String, String>>(json, type)
    }
}
