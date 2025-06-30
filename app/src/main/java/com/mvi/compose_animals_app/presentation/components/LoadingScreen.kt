package com.mvi.compose_animals_app.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mvi.compose_animals_app.ui.theme.Purple40

@Composable
fun LoadingScreen(
    loadingSize: Dp = 100.dp,
    loadingColor: Color = Purple40
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoadingIndicator(
            size = loadingSize,
            color = loadingColor
        )
    }
}

// region Previews

@Preview
@Composable
private fun Preview() {
    LoadingScreen()
}

// endregion Previews
