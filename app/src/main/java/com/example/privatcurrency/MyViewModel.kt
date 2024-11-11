package com.example.rolldice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.privatcurrency.item.CurrencyItem
import com.example.privatcurrency.retrofit.RetrofitPrivate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainViewModel : ViewModel() {
    private val retrofitObject = RetrofitPrivate()

    private val _currencyList = MutableLiveData<List<CurrencyItem>>()
    val currencyList: LiveData<List<CurrencyItem>> get() = _currencyList

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    init {
        // Set today's date as default and fetch currency data
        val today = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        _selectedDate.value = dateFormat.format(today.time)
        fetchData()
    }

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        _selectedDate.value = dateFormat.format(calendar.time)

        fetchData()
    }

    private fun fetchData() {
        retrofitObject.getCurrencyExhange {
            _currencyList.value = it ?: listOf()
        }
    }
}
