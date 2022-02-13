package com.starapps.mvvmkotlin2.repository.db.model.converter


import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem
import java.util.jar.Attributes


class ImageItemConverter {
    @TypeConverter
    fun stringToName(json: String): ImageListResponseItem? {
        val gson = Gson()
        val type = object : TypeToken<ImageListResponseItem>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun nameToString(name: ImageListResponseItem): String {
        val gson = Gson()
        val type = object : TypeToken<ImageListResponseItem>() {}.type
        return gson.toJson(name, type)
    }
}
