package com.surendra.corpusassignmenttask.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.surendra.corpusassignmenttask.data.repository.PreferenceManager
import com.surendra.corpusassignmenttask.databinding.ActivityLoginBinding
import com.surendra.corpusassignmenttask.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.etMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val mobile = s.toString().trim()
                viewModel.validateMobileNumber(mobile)
            }
        })

        binding.etOtp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString().trim()
                if (otp.isNotEmpty()) {
                    proceedToHome()
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel.isMobileValid.observe(this) { isValid ->
            binding.tilOtp.isVisible = isValid
            if (isValid) {
                preferenceManager.saveMobileNumber(binding.etMobile.text.toString())
            }
        }
    }

    private fun proceedToHome() {
        preferenceManager.setUserLoggedIn(true)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}