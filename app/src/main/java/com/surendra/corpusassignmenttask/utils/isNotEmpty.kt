package com.surendra.corpusassignmenttask.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun EditText.isNotEmpty(): Boolean {
    return this.text?.isNotEmpty() == true
}

fun EditText.showErrorIfEmpty(errorMessage: String = "Required"): Boolean {
    return if (!this.isNotEmpty()) {
        this.error = errorMessage
        false
    } else true
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
