package com.example.privatcurrency.retrofit

import android.util.Log
import com.example.privatcurrency.item.CurrencyItem
import com.example.privatcurrency.item.CurrencyObject
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitPrivate {

    //https://api.privatbank.ua/p24api/exchange_rates?date=01.12.2014

    private val BASE_URL = "https://api.privatbank.ua/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var service = retrofit.create(ServicePrivate::class.java)

    fun getCurrencyExhange(data: String, resultCallback: (CurrencyObject?) -> Unit) {
        val call = service.getCurrencyExhange(data)
        // Log.d("XXX", "onParam: $data")

        call?.enqueue(object: Callback<CurrencyObject> {
            override fun onResponse(
                p0: Call<CurrencyObject>,
                response: Response<CurrencyObject>
            ) {
                // Log.d("XXX", "onResponse: ${response}")
                Log.d("XXX", "onResponse: ${response.body()}")
                resultCallback(response.body())
            }

            override fun onFailure(p0: Call<CurrencyObject>?, throwable: Throwable) {
                Log.d("XXX", "onFailure: ${throwable}")
                resultCallback(null)
            }

        })
    }


}