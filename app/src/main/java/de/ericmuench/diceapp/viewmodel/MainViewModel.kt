package de.ericmuench.diceapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val random = Random(System.currentTimeMillis())

    private val _number = MutableStateFlow<Int>(1)
    val number : StateFlow<Int> = _number

    fun nextValue(){
        _number.value = random.nextInt(1,7)
    }
}