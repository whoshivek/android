package com.shivek.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(

    val name : String,
    val age : Int,
    val address : String,
    val number : String,
    @PrimaryKey(autoGenerate = true)
    val id :Long = 0L

)