package com.example.composeamiibo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun MenuAction(
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int,
    modifier: Modifier
) {
    Image(
        painterResource(icon),
        contentDescription = stringResource(contentDescription),
        modifier = modifier
    )
}
