package com.example.privatcurrency

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.privatcurrency.databinding.ActivityMainBinding
import com.example.privatcurrency.item.CurrencyItem
import com.example.privatcurrency.retrofit.RetrofitObject
import com.example.privatcurrency.retrofit.RetrofitPrivate
import com.example.rolldice.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            viewModel.fetchData()
        }

        viewModel.currencyList.observe(this) {
            Log.d(TAG, "onCreate: $it")

            if (it == null || it.isEmpty())
                return@observe
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

    val TAG = "XXX"

}