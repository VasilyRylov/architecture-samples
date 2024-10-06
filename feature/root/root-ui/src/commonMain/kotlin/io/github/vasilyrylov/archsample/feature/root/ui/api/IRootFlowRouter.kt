package io.github.vasilyrylov.archsample.feature.root.ui.api

import io.github.vasilyrylov.archsample.common.ui.navigation.IRouter

interface IRootFlowRouter: IRouter {
    fun toAuth()
    fun toTodo()
}
