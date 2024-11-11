
package com.example.rolldice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.privatcurrency.item.CurrencyItem
import com.example.privatcurrency.retrofit.RetrofitPrivate
// import com.example.rolldice.model.DiceModel
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    private val retrofitObject: RetrofitPrivate = RetrofitPrivate()

    private val _currencyList = MutableLiveData<List<CurrencyItem>>(listOf())
    val currencyList: LiveData<List<CurrencyItem>>
        get() = _currencyList

    fun fetchData() {
        retrofitObject.getCurrencyExhange {
            if (it == null) {
                _currencyList.value = listOf()
            } else {
                _currencyList.value = it
            }
        }
    }
}
