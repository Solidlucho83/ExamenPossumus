package com.luiscastaneriademo.albumsphotoslist.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luiscastaneriademo.albumsphotoslist.data.Repo


class VMFactory(private val repo: Repo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }


}