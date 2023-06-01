package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ UserData::class], version = 1)
abstract class User : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        fun getDb(fragment: Fragment): User {
            return Room.databaseBuilder(
                fragment.requireContext(),
                User::class.java,
                "userdata_db"
            ).build()
        }

        fun getDb(activity: AppCompatActivity): User {
            val context = activity.applicationContext
            return Room.databaseBuilder(
                context,
                User::class.java,
                "userdata_db"
            ).build()
        }
    }
}
