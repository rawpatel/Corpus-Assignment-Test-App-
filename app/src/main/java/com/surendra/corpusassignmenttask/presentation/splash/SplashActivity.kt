package com.surendra.corpusassignmenttask.presentation.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.surendra.corpusassignmenttask.presentation.login.LoginActivity
import com.surendra.corpusassignmenttask.presentation.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install splash screen
        val splashScreen = installSplashScreen()

        // Keep splash screen visible for 10 seconds
        splashScreen.setKeepOnScreenCondition { true }

        // Delay for 10 seconds then check login status
        lifecycleScope.launch {
            delay(10000) // 10 seconds
            checkLoginStatus()
        }
    }

    private fun checkLoginStatus() {
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

        val intent = if (isLoggedIn) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}