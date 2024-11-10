package com.example.privatcurrency

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.privatcurrency.databinding.ActivityMainBinding
import com.example.privatcurrency.retrofit.RetrofitObject
import com.example.rolldice.viewmodel.DiceViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {


    private val viewModel: DiceViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            retrofitObject?.getAndroid("f4c1567c26f6962e0f40") {
                Log.d("XXX", "onResponse: ${it}")
            }
        }

//        viewModel.diceSides.observe(this, Observer { diceModel ->
//            // Log.d("XXX", "onCreate: ${diceModel.toString()}") - debug
//        })

    }
    private var retrofitObject: RetrofitObject? = null
    override fun onStart() {
        super.onStart()

        retrofitObject = RetrofitObject()
    }

    override fun onStop() {
        super.onStop()

        retrofitObject = null
    }
}