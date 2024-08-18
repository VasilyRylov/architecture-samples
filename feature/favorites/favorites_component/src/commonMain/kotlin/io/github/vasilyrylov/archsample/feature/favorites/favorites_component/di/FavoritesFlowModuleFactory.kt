package io.github.vasilyrylov.archsample.feature.favorites.favorites_component.di

import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.favorites.favorites_ui.api.IFavoritesRouter
import org.koin.dsl.module

internal fun createFavoritesFlowModule() = module {
    single<RouterHolder<IFavoritesRouter>> { RouterHolder(null) }
}