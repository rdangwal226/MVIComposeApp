package com.mvi.compose_animals_app

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import com.mvi.compose_animals_app.presentation.navigation.AppNavigation
import com.mvi.compose_animals_app.ui.theme.KotlinComposeMVIAnimalsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setWhiteIconsOnStatusBar(window)
        setContent {
            KotlinComposeMVIAnimalsAppTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AppNavigation()
    }
}

private fun setWhiteIconsOnStatusBar(window: Window) {
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
}
