package com.example.privatcurrency.item


import com.google.gson.annotations.SerializedName

data class AndroidItem(
    @SerializedName("Android")
    val android: String? = null,
    @SerializedName("hello")
    val hello: String? = null,
    @SerializedName("version")
    val version: Int? = null
)