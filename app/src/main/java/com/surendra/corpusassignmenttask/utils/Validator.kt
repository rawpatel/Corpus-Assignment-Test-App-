package com.surendra.corpusassignmenttask.utils

object Validator {

    fun isValidMobile(mobile: String): Boolean {
        return mobile.length == 10 && mobile.all { it.isDigit() }
    }

    fun isValidOTP(otp: String): Boolean {
        return otp.length in 4..6 && otp.all { it.isDigit() }
    }
}
