package com.shivek.todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TodoDao
{
    @Insert
    suspend fun insert(todoUSER: TodoUSER) :Long

    @Insert
    fun insertall(list: List<TodoUSER>)

    @Query("Select * from todouser where isFinished ==0")
    fun task() : LiveData<List<TodoUSER>>

    @Query("update todouser set isFinished=1 where id=:uid")
    fun update(uid : Long)

    @Query("select *from todouser where isFinished==1")
    fun history() : LiveData<List<TodoUSER>>

    @Query("delete from todouser  where id=:uid")
    fun delete(uid : Long)

}