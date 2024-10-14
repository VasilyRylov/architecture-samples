package io.github.vasilyrylov.archsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import io.github.vasilyrylov.archsample.feature.root.component.api.RootFlowComponent

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
