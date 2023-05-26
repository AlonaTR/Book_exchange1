package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1)
abstract class Books : RoomDatabase() {
    abstract fun getDao(): Dao
//    abstract fun getUserDao(): UserDao

    companion object {
        fun getDb(fragment: Fragment): Books {
            return Room.databaseBuilder(
                fragment.requireContext(),
                Books::class.java,
                "booksdata_db"
            ).build()
        }

        fun getDb(activity: AppCompatActivity): Books {
            val context = activity.applicationContext
            return Room.databaseBuilder(
                context,
                Books::class.java,
                "booksdata_db"
            ).build()
        }


    }
}
