package com.mvi.compose_animals_app.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mvi.compose_animals_app.ui.theme.Purple40

@Composable
fun LoadingIndicator(
    size: Dp = 100.dp,
    color: Color = Purple40
) {
    CircularProgressIndicator(
        modifier = Modifier.size(size),
        color = color
    )
}

// region Previews

@Preview
@Composable
private fun Preview() {
    LoadingIndicator()
}

// endregion Previews
