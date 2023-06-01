package com.example.myapplication
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    fun insert(book: Data)
    @Query("SELECT * FROM Data")
    fun getAll(): Flow<List<Data>>
    //delete all
    @Query("DELETE FROM Data")
    fun deleteAll()
    //update like
    @Query("UPDATE Data SET `like` = :likeValue WHERE title = :title")
    suspend fun updateLike(title: String, likeValue: Boolean)
<<<<<<< HEAD
=======


>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
}
