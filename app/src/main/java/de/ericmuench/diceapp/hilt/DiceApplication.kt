package de.ericmuench.diceapp.hilt

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp
import java.util.prefs.Preferences

@HiltAndroidApp
class DiceApplication : Application() {
}