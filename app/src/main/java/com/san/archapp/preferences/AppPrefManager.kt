package com.san.archapp.preferences

import android.content.SharedPreferences

class AppPrefManager(private val prefs: SharedPreferences) {


    fun clear() {
        prefs.edit().clear().apply()
    }

    var userId: String?
        get() {
            return prefs.getString("userId", "")
        }
        set(userId) {
            prefs.edit().putString("userId", userId).apply()
        }


    var isInitialized: Boolean?
        get() {
            return prefs.getBoolean("isInitialized", false)
        }
        set(isInitialized) {
            prefs.edit().putBoolean("isInitialized", true).apply()
        }
}