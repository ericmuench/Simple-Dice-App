package de.ericmuench.diceapp.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import de.ericmuench.diceapp.model.DiceDisplayMode
import de.ericmuench.diceapp.respository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _diceDisplayMode = MutableLiveData<DiceDisplayMode>()
    val diceDisplayMode : LiveData<DiceDisplayMode> = _diceDisplayMode

    suspend fun loadSettings() : Unit {
        viewModelScope.launch(Dispatchers.IO){
            settingsRepository.getDiceDisplayMode().collect {
                _diceDisplayMode.postValue(it)
            }
        }

    }
}