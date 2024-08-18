package io.github.vasilyrylov.archsample.feature.tab.tab_component.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.vasilyrylov.archsample.feature.tab.tab_component.TabFlowComponent
import io.github.vasilyrylov.archsample.feature.tab.tab_component.TabFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.todo_component.ToDoFlowScreenComponent

@Composable
fun TabFlowScreenComponent(tabFlowComponent: TabFlowComponent) {
    // Work in progress
    val childStack by tabFlowComponent.router.childStack.subscribeAsState()
    val childInstance = childStack.active.instance
    val onTabClick: ((NavigationTab) -> Unit) = remember(tabFlowComponent) {
        { tab ->
            when (tab) {
                NavigationTab.ToDoList -> tabFlowComponent.router.openToDoList()
                NavigationTab.Favorites -> tabFlowComponent.router.openFavorites()
            }
        }
    }
    val activeTab: NavigationTab = remember(childInstance) {
        when (childInstance) {
            is TabFlowRouter.Child.ToDoList -> NavigationTab.ToDoList
            is TabFlowRouter.Child.Favorites -> NavigationTab.Favorites
        }
    }
    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                activeTab = activeTab,
                onTabClick = onTabClick
            )
        }
    ) { scaffoldPadding ->
        Children(
            modifier = Modifier.padding(scaffoldPadding),
            stack = childStack,
        ) {
            when (val child = it.instance) {
                is TabFlowRouter.Child.ToDoList -> {
                    ToDoFlowScreenComponent(child.component)
                }

                is TabFlowRouter.Child.Favorites -> {
                    Unit
                }
            }
        }
    }
}