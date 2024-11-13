package com.example.rolldice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.privatcurrency.item.CurrencyObject
import com.example.privatcurrency.item.ExchangeRate
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

    // LiveData for selected currency's buy and sell rates
    private val _selectedBuyRate = MutableLiveData<String>()
    val selectedBuyRate: LiveData<String> get() = _selectedBuyRate

    private val _selectedSellRate = MutableLiveData<String>()
    val selectedSellRate: LiveData<String> get() = _selectedSellRate

    init {
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
        fetchData(_selectedDate.value.toString())
    }

    private fun fetchData(date: String) {
        retrofitObject.getCurrencyExhange(date) { response ->
            _currencyList.postValue(response)
        }
    }

    // Function to update selected currency rates
    fun setSelectedCurrencyRates(exchangeRate: ExchangeRate) {
        _selectedBuyRate.value = exchangeRate.purchaseRate?.toString() ?: "N/A"
        _selectedSellRate.value = exchangeRate.saleRate?.toString() ?: "N/A"
    }
}
