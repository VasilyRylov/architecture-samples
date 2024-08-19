package io.github.vasilyrylov.archsample.feature.auth.auth_ui.element.input_field

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.input_name
import io.github.vasilyrylov.archsample.resources.name
import org.jetbrains.compose.resources.stringResource

@Composable
fun NameInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(fontSize = 16.sp),
        singleLine = true,
        placeholder = { Text(text = stringResource(Res.string.name)) },
        leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = stringResource(Res.string.input_name)) }
    )
}
