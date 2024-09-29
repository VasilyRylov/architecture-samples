package io.github.vasilyrylov.archsample.common.component

import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy


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
