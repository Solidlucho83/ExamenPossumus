package com.luiscastaneriademo.albumsphotoslist.data

import com.luiscastaneriademo.albumsphotoslist.data.database.PhotoDao
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoItemEntity
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoList
import com.luiscastaneriademo.albumsphotoslist.data.model.toPhotoList

class LocalDataSource(private val photoDao: PhotoDao) {

    suspend fun getPhotoTitle(): PhotoList {
        return photoDao.getPhotoList().toPhotoList()
    }

    suspend fun savePhotoList(photoItemEntity: PhotoItemEntity){
        photoDao.insertFavorite(photoItemEntity)
    }
}