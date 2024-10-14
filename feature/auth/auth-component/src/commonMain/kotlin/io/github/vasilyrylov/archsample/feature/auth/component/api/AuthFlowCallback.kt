package io.github.vasilyrylov.archsample.feature.auth.component.api

interface AuthFlowCallback {
    suspend fun onAuthCompletion(name: String)
}
