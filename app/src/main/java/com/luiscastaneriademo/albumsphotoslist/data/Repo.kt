package com.luiscastaneriademo.albumsphotoslist.data


import com.luiscastaneriademo.albumsphotoslist.data.model.AlbumModelItem
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoList
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoModelItem

interface Repo {
     suspend fun getAlbumsList(): List<AlbumModelItem>

     suspend fun getPhotosList(): PhotoList


}