package io.github.vasilyrylov.archsample.feature.auth.auth_ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.RegistrationScreenData

@Composable
fun RegistrationScreenContent(
    data: RegistrationScreenData,
    onMailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onRegistrationClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        NameInputField(
            modifier = Modifier.fillMaxWidth(),
            value = data.mail,
            onValueChange = onMailChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInputField(
            value = data.password,
            onValueChange = onPasswordChange,
            placeholder = "Password",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInputField(
            value = data.repeatedPassword,
            onValueChange = onRepeatedPasswordChange,
            placeholder = "Repeat password",
            modifier = Modifier.fillMaxWidth()
        )
        if (!data.errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = data.errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(96.dp))

        if (data.isRegistrationInProgress) {
            CircularProgressIndicator()
        } else {
            SignUpButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onRegistrationClick,
            )
        }
    }
}

@Composable
private fun SignUpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text("Sign up")
    }
}