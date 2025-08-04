package com.surendra.corpusassignmenttask.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surendra.corpusassignmenttask.utils.ValidationUtils

class LoginViewModel : ViewModel() {

    private val _isMobileValid = MutableLiveData<Boolean>()
    val isMobileValid: LiveData<Boolean> = _isMobileValid

    fun validateMobileNumber(mobile: String) {
        _isMobileValid.value = ValidationUtils.isValidMobileNumber(mobile)
    }
}

