package com.luiscastaneriademo.albumsphotoslist.data

import com.luiscastaneriademo.albumsphotoslist.core.RetrofitClient
import com.luiscastaneriademo.albumsphotoslist.data.model.AlbumModelItem
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoModelItem

class RemoteDataSource {

    suspend fun getAlbumByName(): List<AlbumModelItem> {
        return RetrofitClient.webService.getAlbumsList()
    }
    suspend fun getPhotoTitle(): List<PhotoModelItem> {
        return RetrofitClient.webService.getPhotoList()
    }


}