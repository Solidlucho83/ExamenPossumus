package com.luiscastaneriademo.albumsphotoslist.data

import com.luiscastaneriademo.albumsphotoslist.data.model.AlbumModelItem
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoModelItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface WebService {
    @Headers("Accept: application/json")
    @GET("albums")
    suspend fun getAlbumsList(): List<AlbumModelItem>

    @GET("photos")
    suspend fun getPhotoList(): List<PhotoModelItem>





}