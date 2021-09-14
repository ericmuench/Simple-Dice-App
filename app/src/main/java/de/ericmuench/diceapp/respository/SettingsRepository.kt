package de.ericmuench.diceapp.respository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import de.ericmuench.diceapp.R
import de.ericmuench.diceapp.model.DiceDisplayMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext


class SettingsRepository(appContext : Context) {

    //region companion
    companion object{
        private const val DICE_DISPLAY_MODE_DATASTORE_KEY = "DICE_DISPLAYMODE"
    }
    //endregion

    //region variables
    private val dataStore = PreferenceDataStoreFactory.create(
        produceFile = {
            appContext.preferencesDataStoreFile("settings")
        }
    )

    private var diceDisplayMode : DiceDisplayMode? = null
    //endregion

    //region functions
    fun getDiceDisplayMode() : Flow<DiceDisplayMode>{
        val currentMode = diceDisplayMode
        return if(currentMode == null){
            readStringFromDataStore(DICE_DISPLAY_MODE_DATASTORE_KEY).map {
                val mode = if(it == null){
                    DiceDisplayMode.CLASSIC
                }
                else{
                    DiceDisplayMode.valueOf(it)
                }

                diceDisplayMode = mode

                return@map mode
            }
        }
        else{
            flow{
                emit(currentMode)
            }
        }


    }

    suspend fun setDiceDisplayMode(mode : DiceDisplayMode) : Unit{
        if(mode != diceDisplayMode){
            writeStringToDataStore(
                DICE_DISPLAY_MODE_DATASTORE_KEY,
                mode.toString()
            )

            diceDisplayMode = mode
        }
    }

    private fun readStringFromDataStore(key: String) : Flow<String?> {
        val dataStoreKey = stringPreferencesKey(key)
        return dataStore.data.map { it[dataStoreKey] }
    }

    private suspend fun writeStringToDataStore(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }
    //endregion

}