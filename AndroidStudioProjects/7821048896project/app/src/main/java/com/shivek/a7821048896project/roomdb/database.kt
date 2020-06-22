package com.shivek.a7821048896project.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shivek.a7821048896project.DB_CLASS

@Database(entities = [modelclass::class],version = 1)
abstract class database : RoomDatabase()
{


    abstract fun TODOdao() : TODOdao
    companion object {


        @Volatile
        private var INSTANCE:database? = null

        fun getDatabase(context: Context): database {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    DB_CLASS
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}