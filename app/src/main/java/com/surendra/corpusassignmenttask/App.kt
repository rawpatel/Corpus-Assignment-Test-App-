package com.surendra.corpusassignmenttask

import android.app.Application
import com.surendra.corpusassignmenttask.utils.PrefsHelper


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        PrefsHelper.init(this)
    }
}
