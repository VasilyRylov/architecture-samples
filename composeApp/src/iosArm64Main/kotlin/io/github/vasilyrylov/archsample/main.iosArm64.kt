package io.github.vasilyrylov.archsample

import io.github.vasilyrylov.archsample.di.IOsPlatformComponent
import io.github.vasilyrylov.archsample.di.create
import platform.Foundation.NSUserDefaults

actual fun IOsPlatformComponent.Companion.createKmp(userDefaults: NSUserDefaults): IOsPlatformComponent {
    return IOsPlatformComponent::class.create(userDefaults)
}
