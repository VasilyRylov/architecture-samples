package io.github.vasilyrylov.archsample.feature.auth.component

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

// Такой api интерфейс позволяет скрыть вообще всю реализацию и все зависимости внутри модуля и практически ничего не выдавать на ружу. в ui можно получить Modifier и использовать такие компоненты вплоть до кнопок, а не только для целых экранов.
interface AuthFlowComponent {

    @Composable
    fun Ui()

    interface Factory {
        // а сюда можно добавить входные параметры для компонента, роутер или колбэки при необходимости
        fun create(context: ComponentContext): AuthFlowComponent
    }
}
