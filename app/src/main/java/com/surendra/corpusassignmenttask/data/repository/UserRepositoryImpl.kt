package com.surendra.corpusassignmenttask.data.repository

import com.surendra.corpusassignmenttask.domain.repository.UserRepository
import com.surendra.corpusassignmenttask.utils.SharedPrefsHelper

class UserRepositoryImpl : UserRepository {
    override fun isLoggedIn(): Boolean {
        return SharedPrefsHelper.getLoginStatus()
    }
}