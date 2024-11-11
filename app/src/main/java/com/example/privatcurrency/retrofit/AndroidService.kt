package com.example.privatcurrency.retrofit;

import com.example.privatcurrency.item.AndroidItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface AndroidService {
    @GET("{key}")
    fun getAndroid(@Path("key") key: String?) : Call<AndroidItem?>?
}
