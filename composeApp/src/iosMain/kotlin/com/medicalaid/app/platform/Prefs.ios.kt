package com.medicalaid.app.platform

import platform.Foundation.NSUserDefaults

actual object Prefs {
    private val defaults = NSUserDefaults.standardUserDefaults

    actual fun getInt(key: String, default: Int): Int =
        if (defaults.objectForKey(key) == null) default else defaults.integerForKey(key).toInt()

    actual fun putInt(key: String, value: Int) {
        defaults.setInteger(value.toLong(), key)
    }

    actual fun getString(key: String, default: String): String = defaults.stringForKey(key) ?: default

    actual fun putString(key: String, value: String) {
        defaults.setObject(value, key)
    }
}
