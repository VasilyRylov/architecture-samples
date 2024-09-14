package io.github.vasilyrylov.archsample.common.data.preferences

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

class IOsPreferences(settingsDelegate: NSUserDefaults) : IPreferences {

    private val settings: Settings = NSUserDefaultsSettings(settingsDelegate)

    override fun putInt(key: String, value: Int) {
        settings.putInt(key, value)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return settings.getInt(key, defaultValue)
    }

    override fun putLong(key: String, value: Long) {
        settings.putLong(key, value)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return settings.getLong(key, defaultValue)
    }

    override fun putString(key: String, value: String) {
        settings.putString(key, value)
    }

    override fun getString(key: String, defaultValue: String): String {
        return settings.getString(key, defaultValue)
    }

    override fun putBoolean(key: String, value: Boolean) {
        settings.putBoolean(key, value)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return settings.getBoolean(key, defaultValue)
    }

    override fun clear() {
        settings.clear()
    }
}
