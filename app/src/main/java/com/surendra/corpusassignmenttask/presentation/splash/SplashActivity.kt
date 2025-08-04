package com.surendra.corpusassignmenttask.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.surendra.corpusassignmenttask.utils.PreferenceManager
import com.surendra.corpusassignmenttask.presentation.login.LoginActivity
import com.surendra.corpusassignmenttask.presentation.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        preferenceManager = PreferenceManager(this)

        // Keep splash screen visible for 10 seconds
        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launch {
            delay(10000) // 10 seconds
            checkLoginStatus()
        }
    }

    private fun checkLoginStatus() {
        val isLoggedIn = preferenceManager.isUserLoggedIn()

        val intent = if (isLoggedIn) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}