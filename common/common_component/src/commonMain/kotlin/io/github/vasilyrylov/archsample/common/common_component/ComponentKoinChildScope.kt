package io.github.vasilyrylov.archsample.common.common_component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import org.koin.core.component.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.module.Module
import org.koin.core.scope.Scope

class ComponentKoinChildScope : InstanceKeeper.Instance, KoinScopeComponent {
    override val scope: Scope = createScope()

    fun createAndLinkToParentScope(parentScope: Scope, modules: List<Module>): Scope {
        scope.linkTo(parentScope)
        scope.getKoin().loadModules(modules)
        return scope
    }

    override fun onDestroy() {
        if (scope.isNotClosed()) {
            scope.close()
        }
    }
}