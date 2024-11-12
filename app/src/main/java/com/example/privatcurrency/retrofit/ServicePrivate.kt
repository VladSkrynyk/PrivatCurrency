package com.example.privatcurrency.retrofit

import com.example.privatcurrency.item.AndroidItem
import com.example.privatcurrency.item.CurrencyItem
import com.example.privatcurrency.item.CurrencyObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServicePrivate {
    @GET("p24api/exchange_rates")
    fun getCurrencyExhange(
        @Query("date") courseData: String
    ) : Call<CurrencyObject>?
}