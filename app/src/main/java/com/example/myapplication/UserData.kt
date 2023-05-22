package com.example.myapplication
import androidx.room.*
import java.io.Serializable

@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "imageId")
    val imageId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "year_of_publishing")
    val year_of_publishing: String,
    @ColumnInfo(name = "about_book")
    val about_book: String
) : Serializable
