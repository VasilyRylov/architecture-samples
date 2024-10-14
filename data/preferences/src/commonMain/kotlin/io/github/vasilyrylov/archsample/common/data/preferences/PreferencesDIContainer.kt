package io.github.vasilyrylov.archsample.common.data.preferences

interface IPreferencesSource {
    val preferences: IPreferences
}

interface PreferencesDI {
    val preferences: IPreferences

    companion object {
        val Instance: PreferencesDI get() = PreferencesDIContainer
    }
}

object PreferencesDIContainer : PreferencesDI {

    private var _source: IPreferencesSource? = null
    private inline val source: IPreferencesSource
        get() = _source
            ?: error("DatabaseComponent must be init before usage")

    fun IPreferencesSource.deploy() {
        _source = this
    }

    override val preferences: IPreferences get() = source.preferences
}
