package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert
    fun insert(user: UserData)
    @Query("SELECT * FROM UserData")
    fun getAll(): Flow<List<UserData>>
    //delete all
    @Query("DELETE FROM UserData")
    fun deleteAll()
}