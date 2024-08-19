package io.github.vasilyrylov.archsample.feature.auth.auth_ui.element.input_field

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.input_password
import org.jetbrains.compose.resources.stringResource

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = stringResource(Res.string.input_password)) },
        visualTransformation = PasswordVisualTransformation()
    )
}
