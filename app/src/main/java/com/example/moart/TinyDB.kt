package com.example.moart

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class TinyDB(context: Context) {
    var pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getInt(str: String?, i2: Int): Int {
        return pref.getInt(str, i2)
    }

    fun getBool(str: String): Boolean {
        return pref.getBoolean(str, false)
    }

    fun setBool(str: String, z9: Boolean) {
        str.javaClass
        pref.edit().putBoolean(str, z9).apply()
    }

    fun setInt(str: String?, i2: Int) {
        pref.edit().putInt(str, i2).apply()
    }
}