package io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.component.UserAuthorizedScreenContent
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.UserAuthorizedScreenData

@Composable
fun UserAuthorizedScreen(
    data: UserAuthorizedScreenData,
    logout: () -> Unit,
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            UserAuthorizedScreenContent(
                data,
                onLogoutClick = { logout() }
            )
        }
    }
}