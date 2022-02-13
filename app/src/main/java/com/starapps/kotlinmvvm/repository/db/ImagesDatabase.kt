package com.starapps.mvvmkotlin2.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem
import com.starapps.mvvmkotlin2.repository.db.model.converter.*
import java.lang.annotation.Native
import java.util.jar.Attributes


@Database(
    entities = [ImageListResponseItem::class],
    version = 1, exportSchema = false
)

@TypeConverters(
    Converters::class,
    ImageItemConverter::class,)
abstract class ImagesDatabase : RoomDatabase() {
    abstract val imgDao: ImagesDao
}