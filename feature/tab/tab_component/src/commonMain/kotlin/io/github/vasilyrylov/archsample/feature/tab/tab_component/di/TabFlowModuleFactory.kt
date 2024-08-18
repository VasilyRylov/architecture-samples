package io.github.vasilyrylov.archsample.feature.tab.tab_component.di

import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.tab.tab_ui.ITabFlowRouter
import org.koin.dsl.module

internal fun createTabFlowModule() = module {
    single<RouterHolder<ITabFlowRouter>> { RouterHolder(null) }
}