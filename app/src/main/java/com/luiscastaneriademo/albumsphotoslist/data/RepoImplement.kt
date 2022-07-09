package com.luiscastaneriademo.albumsphotoslist.data

import com.luiscastaneriademo.albumsphotoslist.core.InternetCheck
import com.luiscastaneriademo.albumsphotoslist.data.model.AlbumModelItem
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoList
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoModelItem
import com.luiscastaneriademo.albumsphotoslist.data.model.toPhotoEntity

class RepoImplement(
    private val dataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repo {


    override suspend fun getAlbumsList(): List<AlbumModelItem> {
        return dataSource.getAlbumByName()
    }

    override suspend fun getPhotosList(): PhotoList {
        return if (InternetCheck.internetIsok()) {

            dataSource.getPhotoTitle().forEach {
                localDataSource.savePhotoList(it.toPhotoEntity())
            }
             localDataSource.getPhotoTitle()
        }else{
            localDataSource.getPhotoTitle()
        }
    }



}