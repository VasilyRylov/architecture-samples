package io.github.vasilyrylov.archsample.feature.root.component

import io.github.vasilyrylov.archsample.common.ui.navigation.IRouter

interface IRootFlowRouter: IRouter {
    fun toAuth()
    fun toTodo()
}
