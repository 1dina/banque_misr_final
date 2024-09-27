package com.example.speedotransfer.ui
import android.app.Application
import android.content.Context

class CustomApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: CustomApplication
        fun getAppContext(): Context = instance.applicationContext
    }
}
