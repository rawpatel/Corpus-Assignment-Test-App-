package com.surendra.corpusassignmenttask.utils


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment

object ExtensionsUtils {
    fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    fun Fragment.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        requireContext().showToast(message, length)
    }

    fun Context.openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            showToast("Cannot open URL: $url")
        }
    }

    fun Fragment.openUrl(url: String) {
        requireContext().openUrl(url)
    }
}
