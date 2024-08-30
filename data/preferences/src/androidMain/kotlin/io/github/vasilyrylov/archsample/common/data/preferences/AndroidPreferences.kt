package io.github.vasilyrylov.archsample.common.data.preferences

import android.content.SharedPreferences

class AndroidPreferences(
    private val sharedPreferences: SharedPreferences
) : IPreferences {
    override fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    override fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun getLong(key: String, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defValue: String): String {
        return sharedPreferences.getString(key, defValue)!!
    }

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}