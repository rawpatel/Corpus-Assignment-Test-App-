package com.surendra.corpusassignmenttask.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.surendra.corpusassignmenttask.R
import com.surendra.corpusassignmenttask.databinding.ActivitySplashBinding
import com.surendra.corpusassignmenttask.presentation.login.LoginActivity
import com.surendra.corpusassignmenttask.presentation.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(10000) // Show splash for 10 seconds
            val isLoggedIn = viewModel.isUserLoggedIn()
            val nextIntent = if (isLoggedIn) {
                Intent(this@SplashActivity, MainActivity::class.java)
            } else {
                Intent(this@SplashActivity, LoginActivity::class.java)
            }
            startActivity(nextIntent)
            finish()
        }
    }
}