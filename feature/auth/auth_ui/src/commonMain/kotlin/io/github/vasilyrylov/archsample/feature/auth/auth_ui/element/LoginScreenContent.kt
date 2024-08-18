package io.github.vasilyrylov.archsample.feature.auth.auth_ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.LoginScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.element.input_field.NameInputField
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.element.input_field.PasswordInputField
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.login
import io.github.vasilyrylov.archsample.resources.password
import io.github.vasilyrylov.archsample.resources.sign_in
import io.github.vasilyrylov.archsample.resources.sign_up
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreenContent(
    data: LoginScreenData,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(height = 64.dp))

        LoginText()

        Spacer(modifier = Modifier.height(height = 64.dp))
        NameInputField(
            modifier = Modifier.fillMaxWidth(),
            value = data.name,
            onValueChange = onNameChange
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        PasswordInputField(
            modifier = Modifier.fillMaxWidth(),
            value = data.password,
            placeholder = stringResource(Res.string.password),
            onValueChange = onPasswordChange
        )

        if (!data.errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(height = 16.dp))
            Text(
                text = data.errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(height = 32.dp))
        SignUpTextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSignUpClick
        )
        Spacer(modifier = Modifier.height(height = 32.dp))

        if (data.isAuthenticationInProgress) {
            CircularProgressIndicator()
        } else {
            SignInButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSignInClick
            )
        }
    }
}

@Composable
private fun LoginText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.login),
        fontSize = 20.sp
    )
}

@Composable
private fun SignInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = stringResource(Res.string.sign_in))
    }
}

@Composable
private fun SignUpTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        TextButton(onClick = onClick) {
            Text(text = stringResource(Res.string.sign_up))
        }
    }
}
