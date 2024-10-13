package io.github.vasilyrylov.archsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import io.github.vasilyrylov.archsample.di.AppComponent
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent

class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val rootFlowComponent = RootFlowComponent.DI.factory.create(defaultComponentContext())

        setContent {
            ComposeApp(rootFlowComponent = rootFlowComponent)
        }
    }
}
