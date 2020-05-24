package com.shivek.todoproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface dao{

    @Insert
    suspend fun insert(user: user): Long



    @Query("SELECT * FROM user")
    fun select() : LiveData<List<user>>



}