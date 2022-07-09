package com.luiscastaneriademo.albumsphotoslist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoItemEntity


@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo_table")
    suspend fun getPhotoList(): List<PhotoItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(photo:PhotoItemEntity)

}