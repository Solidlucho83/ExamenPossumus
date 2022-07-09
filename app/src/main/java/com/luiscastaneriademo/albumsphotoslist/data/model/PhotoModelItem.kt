package com.luiscastaneriademo.albumsphotoslist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PhotoModelItem(
    val id: Int,
    val albumId: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

data class PhotoList(val result: List<PhotoModelItem> = listOf())

@Entity(tableName = "photo_table")
data class PhotoItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id") val id: Int = 0,
    @ColumnInfo(name ="albumId") val albumId: Int = 0,
    @ColumnInfo(name ="thumbnailurl")val thumbnailUrl: String?,
    @ColumnInfo(name ="title") val title: String?,
    @ColumnInfo(name ="url") val url: String?)

fun List<PhotoItemEntity>.toPhotoList(): PhotoList{
    val resultList= mutableListOf<PhotoModelItem>()
    this.forEach{
        resultList.add(it.toPhoto())
    }
    return PhotoList(resultList)
}


fun PhotoItemEntity.toPhoto(): PhotoModelItem = PhotoModelItem(
    this.id,
    this.albumId,
    this.thumbnailUrl!!,
    this.title!!,
    this.url!!

)
fun PhotoModelItem.toPhotoEntity(): PhotoItemEntity = PhotoItemEntity(
    this.id,
    this.albumId,
    this.thumbnailUrl,
    this.title,
    this.url

)