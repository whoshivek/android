package com.shivek.a7821048896project.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TODOdao {

        @Insert
        suspend fun insert(modelclass: modelclass) :Long

    @Query("Select * from modelclass")
    fun task() : LiveData<List<modelclass>>

    @Query("update modelclass set ischecked=1 where id=:uid")
    fun update(uid : Long)
}