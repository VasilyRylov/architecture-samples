package io.github.vasilyrylov.archsample.feature.auth.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.vasilyrylov.archsample.feature.auth.ui.element.LoginScreenContent
import io.github.vasilyrylov.archsample.feature.auth.ui.data.LoginViewState

@Composable
fun LoginScreen(
    viewState: LoginViewState,
    onChangeLoginData: (String, String) -> Unit,
    startAuthenticating: () -> Unit,
    toRegistration: () -> Unit,
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            LoginScreenContent(data = viewState,
                onNameChange = { name ->
                    onChangeLoginData(name, viewState.password)
                },
                onPasswordChange = { password ->
                    onChangeLoginData(viewState.name, password)
                },
                onSignInClick = { startAuthenticating() },
                onSignUpClick = { toRegistration() })
        }
    }
}
