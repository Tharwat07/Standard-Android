package com.example.standardproject.utilities

import android.content.Context
import androidx.core.content.edit

class SharedPreferencesManager private constructor(context: Context) {
    var token: String?
        get() = pref.getString("token", null)
        set(value) = pref.edit { putString("token", value) }

    var userData: String?
        get() = pref.getString("userData", null)
        set(value) = pref.edit { putString("userData", value) }

    var language: String
        get() = pref.getString("language", "en") ?: "en"
        set(value) = pref.edit().putString("language", value).apply()


    private val pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    fun clear() = pref.edit { clear() }

    companion object :
        SingletonHolder<SharedPreferencesManager, Context>(::SharedPreferencesManager) {
        private const val FILE_NAME = "myPref"
    }
}