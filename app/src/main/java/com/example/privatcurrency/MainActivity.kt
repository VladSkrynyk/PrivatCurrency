package com.example.privatcurrency

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.activity.viewModels
import java.text.SimpleDateFormat
import java.util.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.privatcurrency.databinding.ActivityMainBinding
import com.example.rolldice.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start button click listener to fetch data
        binding.startButton.setOnClickListener {
            viewModel.fetchData()  // Fetch data
        }

        // Select Date button click listener
        binding.selectDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)

                    // Ensure the selected date is no later than today
                    val today = Calendar.getInstance()
                    if (selectedDate.after(today)) {
                        Toast.makeText(this, "You cannot select a future date", Toast.LENGTH_SHORT).show()
                        return@DatePickerDialog
                    }

                    // Format and display the selected date
                    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    val formattedDate = dateFormat.format(selectedDate.time)
                    binding.selectedDate.text = "Selected Date: $formattedDate"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Set maximum date to today's date to avoid future date selection
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

            // Show the date picker dialog
            datePickerDialog.show()
        }

        // Observe changes in the currency list
        viewModel.currencyList.observe(this) {
            if (it == null || it.isEmpty()) return@observe
            with(binding) {
                it[0].let { currencyItem ->
                    ccy.text = currencyItem.ccy
                    baseCcy.text = currencyItem.baseCcy
                    buy.text = currencyItem.buy
                    sale.text = currencyItem.sale
                }
            }
        }
    }
}
