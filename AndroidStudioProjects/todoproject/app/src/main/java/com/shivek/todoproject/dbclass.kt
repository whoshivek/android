package com.shivek.todoproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= [user::class],version = 1)
abstract class dbclass : RoomDatabase()
{

abstract fun userdao() : dao

    companion object {


        @Volatile
        private var INSTANCE:dbclass? = null

        fun getDatabase(context: Context): dbclass {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                   dbclass::class.java,
                    "file.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
