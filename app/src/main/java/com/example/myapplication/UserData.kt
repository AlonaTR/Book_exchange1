package com.example.myapplication
import androidx.room.*
import java.io.Serializable

@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "imageId")
    val imageId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "lastname")
    val lastname: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone")
    val phone: String
) : Serializable
