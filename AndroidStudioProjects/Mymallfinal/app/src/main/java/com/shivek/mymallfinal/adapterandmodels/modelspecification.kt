package com.shivek.mymallfinal.adapterandmodels

import java.io.Serializable

data class modelspecification(
val heading : String? =null,
val subdata :ArrayList<subheading>
) : Serializable

data class subheading(
    val topic : String? = null ,
    val title : String? = null
) : Serializable