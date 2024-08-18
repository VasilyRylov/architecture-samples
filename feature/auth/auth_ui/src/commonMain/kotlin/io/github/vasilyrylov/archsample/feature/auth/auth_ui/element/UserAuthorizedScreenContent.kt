package io.github.vasilyrylov.archsample.feature.auth.auth_ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.UserAuthorizedScreenData
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.logout
import io.github.vasilyrylov.archsample.resources.welcome
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserAuthorizedScreenContent(
    state: UserAuthorizedScreenData,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(height = 128.dp))
        Text(
            text = stringResource(Res.string.welcome),
            fontSize = 26.sp,
        )
        Text(
            text = state.name,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(height = 32.dp))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLogoutClick
        ) {
            Text(
                text = stringResource(Res.string.logout),
                fontSize = 18.sp
            )
        }
    }
}
