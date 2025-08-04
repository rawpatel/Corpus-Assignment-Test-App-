package com.surendra.corpusassignmenttask.utils

object ValidationUtils {

    fun isValidMobileNumber(mobile: String): Boolean {
        return mobile.length == 10 && mobile.all { it.isDigit() }
    }

    fun isValidOtp(otp: String): Boolean {
        return otp.length == 6 && otp.all { it.isDigit() }
    }
}
