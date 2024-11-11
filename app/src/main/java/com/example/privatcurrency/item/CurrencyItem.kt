package com.example.privatcurrency.item


import com.google.gson.annotations.SerializedName

data class CurrencyItem(
    @SerializedName("base_ccy")
    val baseCcy: String?,
    @SerializedName("buy")
    val buy: String?,
    @SerializedName("ccy")
    val ccy: String?,
    @SerializedName("sale")
    val sale: String?
)