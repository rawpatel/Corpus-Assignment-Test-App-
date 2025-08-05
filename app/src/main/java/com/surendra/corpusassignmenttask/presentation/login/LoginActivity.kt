package com.surendra.corpusassignmenttask.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.surendra.corpusassignmenttask.utils.PreferenceManager
import com.surendra.corpusassignmenttask.databinding.ActivityLoginBinding
import com.surendra.corpusassignmenttask.presentation.main.MainActivity
import com.surendra.corpusassignmenttask.utils.ValidationUtils

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        //  Auto-navigate if already logged in
        if (preferenceManager.isUserLoggedIn()) {
            navigateToMainScreen()
            return
        }

        // Setup input listeners and observers
        setupInputListeners()
        observeViewModel()
    }

    /**
     * Set up all EditText listeners for mobile and OTP inputs
     */
    private fun setupInputListeners() {

        //  Mobile number input validation
        binding.editTextPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val mobile = s.toString().trim()
                viewModel.validateMobileNumber(mobile)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // OTP input listener
        binding.editTextOtp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString().trim()

                if (ValidationUtils.isValidOtp(otp)) {
                    //  Save state and go to main screen
                    preferenceManager.setUserLoggedIn(true)
                    navigateToMainScreen()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    /**
     * Observe LiveData from ViewModel
     */
    private fun observeViewModel() {
        viewModel.isMobileValid.observe(this) { isValid ->

            if (isValid) {
                //  Show OTP field if mobile is valid
                binding.otpInputLayout.isVisible = true
                binding.phoneInputLayout.error = null
                binding.btnLogin.isEnabled = true
            } else {
                // Show error
                binding.otpInputLayout.isVisible = false
                binding.phoneInputLayout.error = "Please enter valid 10-digit mobile number"
                binding.btnLogin.isEnabled = false
            }
        }
    }

    /**
     * Navigate to MainActivity and finish this screen
     */
    private fun navigateToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
