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

//    private val diceResources = intArrayOf(
//        R.drawable.dice_1,
//        R.drawable.dice_2,
//        R.drawable.dice_3,
//        R.drawable.dice_4,
//        R.drawable.dice_5,
//        R.drawable.dice_6
//    )

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
//
//            diceModel.sides.forEachIndexed { index, side ->
//                // val resId = resources.getIdentifier("dice_$side", "drawable", packageName)
//
//                // Log.d("XXX", "onCreate: $side") - debug
//                val resId = diceResources[side - 1]
//
//                when (index) {
//                    0 -> binding.dice1.setImageResource(resId)
//                    1 -> binding.dice2.setImageResource(resId)
//                    2 -> binding.dice3.setImageResource(resId)
//                    3 -> binding.dice4.setImageResource(resId)
//                    4 -> binding.dice5.setImageResource(resId)
//                }
//            }
//        })
//
//        // disable unable logic
//        viewModel.isActiveRoll.observe(this) { isActive ->
//            binding.startButton.isEnabled = isActive
//        }
//        viewModel.isActiveStop.observe(this) { isActive ->
//            binding.stopButton.isEnabled = isActive
//        }
//
//        binding.startButton.setOnClickListener { viewModel.startRolling() }
//        binding.stopButton.setOnClickListener { viewModel.stopRolling() }
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