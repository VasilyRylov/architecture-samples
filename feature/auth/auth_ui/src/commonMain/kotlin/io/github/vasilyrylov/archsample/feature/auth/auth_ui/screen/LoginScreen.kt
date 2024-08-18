package io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.component.LoginScreenContent
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.LoginScreenData

@Composable
fun LoginScreen(
    data: LoginScreenData,
    onChangeLoginData: (String, String) -> Unit,
    startAuthenticating: () -> Unit,
    toRegistration: () -> Unit,
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(padding)
        ) {
            LoginScreenContent(data = data,
                onNameChange = { name ->
                    onChangeLoginData(name, data.password)
                },
                onPasswordChange = { password ->
                    onChangeLoginData(data.name, password)
                },
                onSignInClick = { startAuthenticating() },
                onSignUpClick = { toRegistration() })
        }
    }
}