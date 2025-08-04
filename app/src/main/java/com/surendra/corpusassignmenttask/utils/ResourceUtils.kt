package com.surendra.corpusassignmenttask.utils

import android.content.Context
import androidx.core.content.ContextCompat

object ResourceUtils {

    fun getDimen(context: Context, id: Int): Float {
        return context.resources.getDimension(id)
    }

    fun getColor(context: Context, id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    fun getString(context: Context, id: Int): String {
        return context.getString(id)
    }
}
