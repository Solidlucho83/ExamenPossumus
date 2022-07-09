package com.luiscastaneriademo.albumsphotoslist.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.luiscastaneriademo.albumsphotoslist.core.Resource
import com.luiscastaneriademo.albumsphotoslist.data.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

open class MainViewModel(private val repo: Repo) : ViewModel() {


    val fecthListAlbums = liveData(Dispatchers.IO) {
            emit(Resource.Loading())

        try {
            runBlocking {
                emit(Resource.Success(repo.getAlbumsList()))
            }
        } catch (e: Exception) {
            runBlocking {
                Log.d("error", e.toString())
            }
        }


    }
    fun fechtListPhotos() = liveData(Dispatchers.IO) {

        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getPhotosList()))

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }


    }


}