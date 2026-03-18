package yr.muhammadyaumil.movieapp.data.local.sessions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session_manager")

class SessionManager(
    private val context: Context,
) {
    companion object {
        val USER_ID_KEY = stringPreferencesKey("active_user_id")
    }

    val activeUserIdFlow: Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }

    suspend fun saveUserId(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
        }
    }
}
