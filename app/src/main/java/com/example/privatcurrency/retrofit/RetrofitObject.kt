package com.example.privatcurrency.retrofit

import android.util.Log
import com.example.privatcurrency.item.AndroidItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

class RetrofitObject {

    // https://api.npoint.io/f4c1567c26f6962e0f40

    val BASE_URL = "https://api.npoint.io/"

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(AndroidService::class.java)

    fun getAndroid(key: String?, responseCallback: (AndroidItem?) -> Unit) {
        val call : Call<AndroidItem?>? = service.getAndroid(key)

        call?.enqueue(object : Callback<AndroidItem?>{
            override fun onResponse(p0: Call<AndroidItem?>, p1: Response<AndroidItem?>) {
                Log.d(TAG, "onResponse: $p1")


                responseCallback(p1.body())
            }

            override fun onFailure(p0: Call<AndroidItem?>, p1: Throwable) {
                Log.d(TAG, "onFailure: $p1")
            }

        })
    }

    val TAG = "XXX"
}