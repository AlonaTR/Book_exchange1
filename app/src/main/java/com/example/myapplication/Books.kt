package com.example.myapplication


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Data::class, UserData::class], version = 1)
abstract class Books : RoomDatabase() {
    abstract fun getDao(): Dao
    abstract fun getUserDao(): UserDao

    companion object {
        fun getDb(fragment: FragmentMain): Books {
            return Room.databaseBuilder(
                fragment.requireContext(),
                Books::class.java,
                "booksdata_db"
            ).build()
        }
    }
}