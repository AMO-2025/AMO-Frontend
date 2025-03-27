package util

import com.russhwolf.settings.Settings
import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

class TokenManager {
    private val preferences = Preferences.userNodeForPackage(TokenManager::class.java)
    private val settings = PreferencesSettings(preferences)
    private val tokenKey = "authToken"
    
    fun saveToken(token: String) {
        try {
            settings.putString(tokenKey, token)
        } catch (e: Exception) {
            // 토큰 저장 실패 시 로그 기록
            println("토큰 저장 실패: ${e.message}")
        }
    }
    
    fun getToken(): String? {
        return try {
            if (settings.hasKey(tokenKey)) {
                settings.getString(tokenKey, "")
            } else {
                null
            }
        } catch (e: Exception) {
            // 토큰 조회 실패 시 로그 기록 후 null 반환
            println("토큰 조회 실패: ${e.message}")
            null
        }
    }
    
    fun clearToken() {
        try {
            settings.remove(tokenKey)
        } catch (e: Exception) {
            // 토큰 삭제 실패 시 로그 기록
            println("토큰 삭제 실패: ${e.message}")
        }
    }
}