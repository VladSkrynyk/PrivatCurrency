
package com.example.rolldice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rolldice.model.DiceModel
import kotlinx.coroutines.*

class DiceViewModel : ViewModel() {

    private val _diceSides = MutableLiveData<DiceModel>()
    val diceSides: LiveData<DiceModel> get() = _diceSides

    private val _isActiveRoll = MutableLiveData<Boolean>(true)
    val isActiveRoll: LiveData<Boolean> get() = _isActiveRoll

    private val _isActiveStop = MutableLiveData<Boolean>(false)
    val isActiveStop: LiveData<Boolean> get() = _isActiveStop

    private var rollingJob: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun startRolling() {
        if (_isActiveRoll.value == true) {
            _isActiveRoll.value = false
            _isActiveStop.value = true
            rollingJob = coroutineScope.launch {
                while (isActive) {
                    _diceSides.value = DiceModel()
                    delay(1_000)
                }
            }
        }
    }

    fun stopRolling() {
        _isActiveRoll.value = true
        _isActiveStop.value = false

        rollingJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
