package com.example.privatcurrency.retrofit

import android.util.Log
import com.example.privatcurrency.item.AndroidItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject {

    // https://api.npoint.io/c6bd8e0a6c002a45b99a

    private val BASE_URL = "https://api.npoint.io/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var service = retrofit.create(AndroidService::class.java)

    fun getAndroid(key: String?, responseCallback: (AndroidItem?) -> Unit) {
        val call : Call<AndroidItem?>? = service.getAndroid(key)

        call?.enqueue(object : Callback<AndroidItem?> {
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