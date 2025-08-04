package com.surendra.corpusassignmenttask.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREF_NAME, Context.MODE_PRIVATE
    )
    
    companion object {
        private const val PREF_NAME = "app_preferences"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_MOBILE_NUMBER = "mobile_number"
    }
    
    fun setUserLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }
    
    fun isUserLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    
    fun saveMobileNumber(mobileNumber: String) {
        prefs.edit().putString(KEY_MOBILE_NUMBER, mobileNumber).apply()
    }
    
    fun getMobileNumber(): String? {
        return prefs.getString(KEY_MOBILE_NUMBER, null)
    }
}
