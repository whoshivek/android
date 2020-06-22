package com.shivek.a7821048896project.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class modelclass(

    var name : String? = null,
    var contact : String? = null,
var image : Int? = null,
var ischecked : Int = 0 ,
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0

)