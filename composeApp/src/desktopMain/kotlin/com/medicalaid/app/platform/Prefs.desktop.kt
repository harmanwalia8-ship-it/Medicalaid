package com.medicalaid.app.platform

import java.util.prefs.Preferences

actual object Prefs {
    private val node: Preferences by lazy { Preferences.userRoot().node("com/medicalaid/app") }

    actual fun getInt(key: String, default: Int): Int = runCatching { node.getInt(key, default) }.getOrDefault(default)

    actual fun putInt(key: String, value: Int) {
        runCatching { node.putInt(key, value) }
    }

    actual fun getString(key: String, default: String): String = runCatching { node.get(key, default) }.getOrDefault(default)

    actual fun putString(key: String, value: String) {
        runCatching { node.put(key, value) }
    }
}
