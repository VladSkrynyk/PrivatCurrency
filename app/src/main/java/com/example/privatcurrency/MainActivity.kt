package com.example.privatcurrency

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.privatcurrency.databinding.ActivityMainBinding
import com.example.rolldice.viewmodel.MainViewModel
import java.util.Calendar
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView and Adapter
        adapter = CurrencyAdapter(emptyList()) { exchangeRate ->
            // Handle item click: Update buy and sell TextViews
            binding.buy.text = "Buy Rate: ${exchangeRate.purchaseRate ?: "N/A"} UAH"
            binding.sale.text = "Sell Rate: ${exchangeRate.saleRate ?: "N/A"} UAH"
        }
        binding.currencyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.currencyRecyclerView.adapter = adapter

        // Observe currency data and update RecyclerView
        viewModel.currencyList.observe(this) { currencyObject ->
            val exchangeRates = currencyObject?.exchangeRate?.filterNotNull() ?: emptyList()
            adapter.updateData(exchangeRates)
        }

        // Observe selected date and display it
        viewModel.selectedDate.observe(this) { date ->
            binding.selectedDate.text = "Selected Date: $date"
        }

        // Date selection setup
        binding.selectDateButton.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val today = Calendar.getInstance()
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                if (selectedDate.after(today)) {
                    Toast.makeText(this, "You cannot select a future date", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.setDate(year, month, dayOfMonth)
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
            show()
        }
    }
}
