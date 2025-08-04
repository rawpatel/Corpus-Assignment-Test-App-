package com.surendra.corpusassignmenttask.utils

import android.net.Uri

object ValidationUtils {

    fun isValidMobileNumber(mobile: String): Boolean {
        return mobile.length == 10 && mobile.all { it.isDigit() }
    }

    fun isValidOtp(otp: String): Boolean {
        return otp.length == 6 && otp.all { it.isDigit() }
    }

    fun isValidUrl(url: String): Boolean {
        return try {
            Uri.parse(url)?.scheme?.let {
                it == "http" || it == "https"
            } ?: false
        } catch (e: Exception) {
            false
        }
    }
}
