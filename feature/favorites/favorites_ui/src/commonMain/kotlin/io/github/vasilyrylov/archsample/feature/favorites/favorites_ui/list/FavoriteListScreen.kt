package io.github.vasilyrylov.archsample.feature.favorites.favorites_ui.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FavoriteListScreen() {
    // Work in progress
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Favorite list") })
        },
    ) { innerPadding ->


    }
}