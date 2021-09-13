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
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext


class SettingsRepository(appContext : Context) {

    //region companion
    companion object{
        private const val DICE_DISPLAY_MODE_DATASTORE_KEY = "DICE_DISPLAYMODE"
    }
    //endregion

    //region variables
    val test = appContext.getString(R.string.goback)

    private val dataStore = PreferenceDataStoreFactory.create(
        produceFile = {
            appContext.preferencesDataStoreFile("settings")
        }
    )
    //endregion

    //region LiveData
    private val _diceDisplayMode = getDiceDisplayMode().asLiveData(Dispatchers.IO)
    val diceDisplayMode : LiveData<DiceDisplayMode> = _diceDisplayMode
    //endregion


    //region functions
    private fun getDiceDisplayMode() : Flow<DiceDisplayMode>{
        return readStringFromDataStore(DICE_DISPLAY_MODE_DATASTORE_KEY).map {
            if(it == null){
                DiceDisplayMode.CLASSIC
            }
            else{
                DiceDisplayMode.valueOf(it)
            }
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