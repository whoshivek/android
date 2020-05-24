package com.shivek.roomdb

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface userdao
{
    @Insert
    suspend fun insert(user: User)

    @Insert
    suspend fun insertall(list: List<User>)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * from user")
   fun select() : LiveData<List<User>>

    @Query("SELECT * from user where age >= :age")
    suspend fun selectspecific(age : Int) :  List<User>

}

