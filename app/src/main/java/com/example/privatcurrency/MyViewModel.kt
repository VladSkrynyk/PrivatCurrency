package com.example.rolldice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.privatcurrency.item.CurrencyItem
import com.example.privatcurrency.retrofit.RetrofitPrivate
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale


class MainViewModel : ViewModel() {
    private val retrofitObject: RetrofitPrivate = RetrofitPrivate()
    private val _currencyList = MutableLiveData<List<CurrencyItem>>(listOf())
    val currencyList: LiveData<List<CurrencyItem>>
        get() = _currencyList

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String>
        get() = _selectedDate

    fun fetchData() {
        retrofitObject.getCurrencyExhange {
            if (it == null) {
                _currencyList.value = listOf()
            } else {
                _currencyList.value = it
            }
        }
    }

    fun setSelectedDate(calendar: Calendar) {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        _selectedDate.value = dateFormat.format(calendar.time)
    }

}
