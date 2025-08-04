package com.surendra.corpusassignmenttask.presentation.splash

import androidx.lifecycle.ViewModel

class SplashViewModel: ViewModel() {

    private val userRepository = UserRepositoryImpl()

    fun isUserLoggedIn(): Boolean {
        return userRepository.isLoggedIn()
    }
}