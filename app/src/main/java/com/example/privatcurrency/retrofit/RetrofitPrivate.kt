package com.example.privatcurrency.retrofit

import com.example.privatcurrency.item.CurrencyItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitPrivate {

    // https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5

    private val BASE_URL = "https://api.privatbank.ua/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var service = retrofit.create(ServicePrivate::class.java)

    fun getCurrencyExhange(resultCallback: (List<CurrencyItem>?) -> Unit) {
        val call = service.getCurrencyExhange(null, 5)

        call?.enqueue(object: Callback<List<CurrencyItem>?> {
            override fun onResponse(
                p0: Call<List<CurrencyItem>?>,
                response: Response<List<CurrencyItem>?>
            ) {
                resultCallback(response.body())
            }

            override fun onFailure(p0: Call<List<CurrencyItem>?>, throwable: Throwable) {
                resultCallback(null)
            }

        })
    }


}