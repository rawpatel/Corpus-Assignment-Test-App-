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
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_LAST_LOGIN = "last_login"
    }

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
        if (isLoggedIn) {
            prefs.edit().putLong(KEY_LAST_LOGIN, System.currentTimeMillis()).apply()
        }
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

    fun saveUserName(userName: String) {
        prefs.edit().putString(KEY_USER_NAME, userName).apply()
    }

    fun getUserName(): String? {
        return prefs.getString(KEY_USER_NAME, null)
    }

    fun getLastLoginTime(): Long {
        return prefs.getLong(KEY_LAST_LOGIN, 0L)
    }

    fun clearUserData() {
        prefs.edit().clear().apply()
    }
}