package io.github.vasilyrylov.archsample.common.common_component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.koinApplication

class ComponentKoinContext : InstanceKeeper.Instance {
    private var koinApp: KoinApplication? = null

    @OptIn(KoinInternalApi::class)
    fun getOrCreateKoinScope(modules: List<Module>): Scope {
        if (koinApp == null) {
            koinApp = koinApplication { modules(modules) }
        }
        return requireNotNull(koinApp).koin.scopeRegistry.rootScope
    }

    override fun onDestroy() {
        koinApp?.close()
    }
}