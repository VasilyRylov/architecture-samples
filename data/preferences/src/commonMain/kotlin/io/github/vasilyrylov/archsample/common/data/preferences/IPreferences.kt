package io.github.vasilyrylov.archsample.common.data.preferences

interface IPreferences {
    fun putInt(key: String, value: Int)
    fun getInt(key: String, defValue: Int = 0): Int
    fun putLong(key: String, value: Long)
    fun getLong(key: String, defValue: Long = 0): Long
    fun putString(key: String, value: String)
    fun getString(key: String, defValue: String = ""): String
    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defValue: Boolean = false): Boolean
    fun clear()
}
