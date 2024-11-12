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
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe date selection to display it on the UI
        viewModel.selectedDate.observe(this) { date ->
            binding.selectedDate.text = "Selected Date: $date"
        }

        // Observe currency list and update UI when data changes
        viewModel.currencyList.observe(this) {
            if (it == null) return@observe
            with(binding) {
                Log.d("XXX", "onCreate: $it")
            }
        }

        // Set up DatePicker to select a date no later than today
        binding.selectDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
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
            )

            // Set max date to today's date
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }
}
