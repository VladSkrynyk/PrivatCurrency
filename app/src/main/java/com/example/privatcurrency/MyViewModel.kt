package com.example.rolldice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.privatcurrency.item.CurrencyItem
import com.example.privatcurrency.item.CurrencyObject
import com.example.privatcurrency.retrofit.RetrofitPrivate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainViewModel : ViewModel() {
    private val retrofitObject = RetrofitPrivate()

    private val _currencyList = MutableLiveData<CurrencyObject?>()
    val currencyList: LiveData<CurrencyObject?> get() = _currencyList

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    init {
        // Set today's date as default and fetch currency data
        val today = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        _selectedDate.value = dateFormat.format(today.time)

        fetchData(_selectedDate.value.toString())
    }

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        _selectedDate.value = dateFormat.format(calendar.time)
        // Log.d("XXX", "setDate: $_selectedDate.value")
        fetchData(_selectedDate.value.toString())
    }

    private fun fetchData(data: String) {
        retrofitObject.getCurrencyExhange(data) {
            _currencyList.value = it // needed to be developed
            // Log.d("XXX", "fetchData: $it")
        }
    }
}
