package com.example.testovoe13

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "df2f9e05-4636-48c1-8525-c2f292cfe02c"

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}