package util

import com.russhwolf.settings.Settings
import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

class TokenManager {
    private val settings: Settings = PreferencesSettings(
        Preferences.userRoot().node("com.amo.autismgame")
    )
    
    private val TOKEN_KEY = "jwt_token"
    
    fun saveToken(token: String) {
        settings.putString(TOKEN_KEY, token)
    }
    
    fun getToken(): String? {
        return if (settings.hasKey(TOKEN_KEY)) settings.getString(TOKEN_KEY, "") else null
    }
    
    fun clearToken() {
        if (settings.hasKey(TOKEN_KEY)) {
            settings.remove(TOKEN_KEY)
        }
    }
}