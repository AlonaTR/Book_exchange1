package com.example.myapplication

import androidx.room.*
import java.io.Serializable

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "imageId")
    val imageId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "yaer_of_publishing")
    val year_of_publishing: String,
    @ColumnInfo(name = "about_book")
    val about_book: String,
    @ColumnInfo(name = "user_name")
    val user_name: String,
    @ColumnInfo(name = "like")
    var like: Boolean

) : Serializable
