package io.github.vasilyrylov.archsample.feature.auth.component

interface AuthFlowCallback {
    suspend fun onAuthCompletion(name: String)
}
