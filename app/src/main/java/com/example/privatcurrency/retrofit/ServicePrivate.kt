package com.example.privatcurrency.retrofit

import com.example.privatcurrency.item.AndroidItem
import com.example.privatcurrency.item.CurrencyItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServicePrivate {
    @GET("p24api/pubinfo")
    fun getCurrencyExhange(
        @Query("exchange") exchange: String?,
        @Query("coursid") coursId: Int
    ) : Call<List<CurrencyItem>?>?
}