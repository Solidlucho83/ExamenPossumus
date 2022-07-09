package com.luiscastaneriademo.albumsphotoslist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoItemEntity

@Database(entities = [PhotoItemEntity::class], version = 1)
abstract class PhotoDataBase : RoomDatabase() {

    abstract fun getPhotoList(): PhotoDao

    companion object {

        private var INSTANCE: PhotoDataBase? = null

        fun getDatabase(context: Context): PhotoDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                PhotoDataBase::class.java,
                "photo_table"
            ).build()

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }


    }

}