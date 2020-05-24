package com.shivek.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TodoUSER::class],version = 1)
abstract class todoroomdatabase : RoomDatabase()
{
    abstract fun tododau(): TodoDao

    companion object {


        @Volatile
        private var INSTANCE:todoroomdatabase? = null

        fun getDatabase(context: Context): todoroomdatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    todoroomdatabase::class.java,
                    DB_CLASS
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}