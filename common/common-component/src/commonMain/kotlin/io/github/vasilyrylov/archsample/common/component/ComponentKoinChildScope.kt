package io.github.vasilyrylov.archsample.common.component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.core.scope.Scope

class ComponentKoinChildScope : InstanceKeeper.Instance {
    private var scope: Scope? = null

    fun getOrCreateKoinChildScope(parentScope: Scope, modules: List<Module>, stateScopeId: String): Scope {
        if (scope != null) return scope!!

        val newScope = parentScope.getKoin().createScope(stateScopeId, StringQualifier(stateScopeId))
        newScope.linkTo(parentScope)
        newScope.getKoin().loadModules(modules)
        scope = newScope
        return newScope
    }

    override fun onDestroy() {
        if (scope?.isNotClosed() != null) {
            scope!!.close()
        }
    }
}