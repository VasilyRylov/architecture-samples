package io.github.vasilyrylov.archsample.common.data.preferences

class IOsPreferences : IPreferences {
    override fun putInt(key: String, value: Int) {
        // TODO("Not yet implemented")
    }

    override fun getInt(key: String, defValue: Int): Int {
        return defValue
    }

    override fun putLong(key: String, value: Long) {
        // TODO("Not yet implemented")
    }

    override fun getLong(key: String, defValue: Long): Long {
        return defValue
    }

    override fun putString(key: String, value: String) {
        // TODO("Not yet implemented")
    }

    override fun getString(key: String, defValue: String): String {
        return defValue
    }

    override fun putBoolean(key: String, value: Boolean) {
        // TODO("Not yet implemented")
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        return defValue
    }

    override fun clear() {
        // TODO("Not yet implemented")
    }
}