package com.example.privatcurrency.item


import com.google.gson.annotations.SerializedName

data class CurrencyObject(
    @SerializedName("bank")
    val bank: String?,
    @SerializedName("baseCurrency")
    val baseCurrency: Int?,
    @SerializedName("baseCurrencyLit")
    val baseCurrencyLit: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("exchangeRate")
    val exchangeRate: List<ExchangeRate?>?
)