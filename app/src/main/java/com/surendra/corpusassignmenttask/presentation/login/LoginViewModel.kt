package com.surendra.corpusassignmenttask.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _isMobileValid = MutableLiveData<Boolean>()
    val isMobileValid: LiveData<Boolean> = _isMobileValid

    fun validateMobileNumber(mobile: String) {
        val isValid = mobile.length == 10 && mobile.all { it.isDigit() }
        _isMobileValid.value = isValid
    }
}