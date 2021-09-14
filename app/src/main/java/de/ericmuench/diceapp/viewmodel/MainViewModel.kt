package de.ericmuench.diceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import de.ericmuench.diceapp.model.DiceDisplayMode
import de.ericmuench.diceapp.respository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val random = Random(System.currentTimeMillis())

    private val _number = MutableStateFlow<Int>(1)
    val number : StateFlow<Int> = _number


    val diceDisplayMode : LiveData<DiceDisplayMode> = settingsRepository
                                                        .getDiceDisplayMode()
                                                        .asLiveData(Dispatchers.IO)

    fun nextValue(){
        _number.value = random.nextInt(1,7)
    }


}