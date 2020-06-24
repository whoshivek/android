package com.shivek.mymallfinal.adapterandmodels

import java.io.Serializable

data class modelspecification(
var heading : String? =null,
var subdata :ArrayList<subheading>
) : Serializable

data class subheading(
    val topic : String? = null ,
    val title : String? = null
) : Serializable