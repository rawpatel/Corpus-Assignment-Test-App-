package com.surendra.corpusassignmenttask.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsHelper {

    private const val PREF_NAME = "my_prefs"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setLoginStatus(loggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply()
    }

    fun getLoginStatus(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}
