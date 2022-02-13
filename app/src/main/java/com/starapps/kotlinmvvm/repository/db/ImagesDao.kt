package com.starapps.mvvmkotlin2.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem


@Dao
interface ImagesDao {



        @Query("SELECT * FROM ImagesTable")
         fun findAll(): List<ImageListResponseItem>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
         fun add(users: List<ImageListResponseItem>)


}