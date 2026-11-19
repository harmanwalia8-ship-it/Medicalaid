package com.medicalaid.app.platform

import platform.Foundation.NSUserDefaults

actual object Prefs {
    private val defaults = NSUserDefaults.standardUserDefaults

    actual fun getInt(key: String, default: Int): Int =
        if (defaults.objectForKey(key) == null) default else defaults.integerForKey(key).toInt()

    actual fun putInt(key: String, value: Int) {
        defaults.setInteger(value.toLong(), key)
    }
}
