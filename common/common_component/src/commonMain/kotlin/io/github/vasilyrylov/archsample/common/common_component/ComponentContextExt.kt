package io.github.vasilyrylov.archsample.common.common_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.common_ui.BaseViewModel
import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import org.koin.core.module.Module
import org.koin.core.scope.Scope


fun <T : Any> ComponentContext.registerAndGetSavedState(
    key: String,
    initialValue: T,
    deserialization: DeserializationStrategy<T>,
    serialization: SerializationStrategy<T>,
    getCurrentState: () -> T
): T {
    stateKeeper.register(key = key, strategy = serialization) {
        getCurrentState()
    }

    return stateKeeper.consume(key = key, strategy = deserialization) ?: initialValue
}

inline fun <reified T : BaseViewModel> ComponentContext.createViewModel(scope: Scope): T {
    return instanceKeeper.getOrCreate {
        ComponentViewModelDecorator(scope.get<T>(T::class))
    }.viewModel
}

class ComponentViewModelDecorator<T : BaseViewModel>(val viewModel: T) : InstanceKeeper.Instance {
    override fun onDestroy() {
        viewModel.onDestroy()
    }
}

fun ComponentContext.createKoinScope(modules: List<Module>): Scope {
    return instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }.getOrCreateKoinScope(modules)
}

fun ComponentContext.createChildScope(parentScope: Scope, modules: List<Module>): Scope {
    return instanceKeeper.getOrCreate {
        ComponentKoinChildScope()
    }.createAndLinkToParentScope(parentScope, modules)
}

fun <T> Scope.updateRouterInstance(router: T) {
    get<RouterHolder<T>>(RouterHolder::class).router = router
}