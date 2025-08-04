package com.surendra.corpusassignmenttask.presentation.splash

import androidx.lifecycle.ViewModel
import com.surendra.corpusassignmenttask.data.repository.UserRepositoryImpl

class SplashViewModel: ViewModel() {

    private val userRepository = UserRepositoryImpl()

    fun isUserLoggedIn(): Boolean {
        return userRepository.isLoggedIn()
    }
}