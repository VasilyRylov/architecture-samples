package io.github.vasilyrylov.archsample.feature.tab.tab_component.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun AppBottomNavigation(
    activeTab: NavigationTab,
    onTabClick: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = activeTab == NavigationTab.ToDoList,
            onClick = { onTabClick(NavigationTab.ToDoList) },
            icon = {
                Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = null)
            }
        )
        NavigationBarItem(
            selected = activeTab == NavigationTab.Favorites,
            onClick = { onTabClick(NavigationTab.Favorites) },
            icon = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            }
        )
    }
}