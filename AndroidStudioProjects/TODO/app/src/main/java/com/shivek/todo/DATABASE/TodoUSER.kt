package com.shivek.todo

import android.content.IntentSender
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoUSER(
    var title : String,
    var description :String,

var date : Long,
    var time : Long,
    var category : String,

    var isFinished: Int = 0,

    @PrimaryKey(autoGenerate = true)
    var id : Long=0

)