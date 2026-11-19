package com.medicalaid.app.platform

import android.content.Context
import android.content.SharedPreferences

actual object Prefs {
    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    actual fun getInt(key: String, default: Int): Int = prefs?.getInt(key, default) ?: default

    actual fun putInt(key: String, value: Int) {
        prefs?.edit()?.putInt(key, value)?.apply()
    }
}
