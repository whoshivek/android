package com.shivek.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [User::class],version = 1)
abstract class roomdatabase : RoomDatabase(){

    abstract fun userdo() : userdao

}