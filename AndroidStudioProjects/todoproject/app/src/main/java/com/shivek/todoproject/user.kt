package com.shivek.todoproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class user
    (
    val task : String,

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L

)

