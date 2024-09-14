package io.github.vasilyrylov.archsample

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import io.github.vasilyrylov.archsample.common.data.preferences.AndroidPreferences
import io.github.vasilyrylov.archsample.data.database.getRoomDatabase
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies

class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val database = getRoomDatabase(this)

        val preferencesName = "${applicationContext.packageName}_preferences"
        val sharedPreferences = applicationContext.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val preferences = AndroidPreferences(sharedPreferences)

        val rootComponentDependencies = object : IRootComponentDependencies {
            override val preferences = preferences
            override val userDao = database.getUserDao()
            override val todoDao = database.getTodoDao()
        }

        val rootFlowComponent = RootFlowComponent(
            componentContext = defaultComponentContext(),
            dependencies = rootComponentDependencies
        )

        setContent {
            ComposeApp(rootFlowComponent = rootFlowComponent)
        }
    }
}
