package com.medicalaid.app.platform

/** Tiny key-value store for app settings only (never patient data). */
expect object Prefs {
    fun getInt(key: String, default: Int): Int
    fun putInt(key: String, value: Int)
    fun getString(key: String, default: String): String
    fun putString(key: String, value: String)
}
