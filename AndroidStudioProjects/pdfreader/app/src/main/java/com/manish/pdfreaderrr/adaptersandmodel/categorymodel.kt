package com.shivek.mymallfinal.adapterandmodels

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

data class categorymodel(
    val link : String? = null,
val text : String? = null,
val strage : StorageReference? = null,

val images : Int? =null

)