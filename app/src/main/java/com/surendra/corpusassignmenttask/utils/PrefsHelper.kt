package com.surendra.corpusassignmenttask.utils

import android.content.Context
import android.content.SharedPreferences

object PrefsHelper {
    private const val PREF_NAME = "app_prefs"
    private const val KEY_IS_LOGGED_IN = "IS_LOGGED_IN"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setLoginStatus(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun clear() {
        prefs.edit().clear().apply()
    }
}
