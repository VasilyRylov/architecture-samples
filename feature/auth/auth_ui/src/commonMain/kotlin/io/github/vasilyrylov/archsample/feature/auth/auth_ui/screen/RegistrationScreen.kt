package io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.component.RegistrationScreenContent
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.RegistrationScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.common.ConfirmDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    data: RegistrationScreenData,
    onBack: () -> Unit,
    handleChangeRegistrationData: (String, String, String) -> Unit,
    startRegistration: () -> Unit,
    declineRegistrationData: () -> Unit,
    confirmRegistrationData: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Registration") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })
    }) { padding ->
        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(padding)
        ) {
            RegistrationScreenContent(data = data,
                onMailChange = { mail ->
                    handleChangeRegistrationData(
                        mail,
                        data.password, data.repeatedPassword
                    )
                },
                onPasswordChange = { password ->
                    handleChangeRegistrationData(
                        data.mail,
                        password, data.repeatedPassword
                    )
                },
                onRepeatedPasswordChange = { repeatedPassword ->
                    handleChangeRegistrationData(
                        data.mail,
                        data.password,
                        repeatedPassword
                    )
                },
                onRegistrationClick = { startRegistration() })
        }
        if (data.isConfirmationRequested) {
            ConfirmDialog(
                title = "Confirm registration",
                description = "Continue with current data?",
                onDismiss = { declineRegistrationData() },
                onConfirm = { confirmRegistrationData() },
                onCancel = { declineRegistrationData() },
            )
        }
    }
}
