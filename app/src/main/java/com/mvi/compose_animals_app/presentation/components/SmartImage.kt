package com.mvi.compose_animals_app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mvi.compose_animals_app.R

@Composable
fun SmartImage(
    modifier: Modifier = Modifier,
    url: String?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderResId: Int = R.drawable.placeholder
) {
    val isInPreview = LocalInspectionMode.current

    when {
        isInPreview || url.isNullOrBlank() -> {
            Image(
                modifier = modifier,
                painter = painterResource(id = placeholderResId),
                contentDescription = contentDescription,
                contentScale = contentScale,
            )
        }

        else -> {
            AsyncImage(
                modifier = modifier,
                model = url,
                contentDescription = contentDescription,
                contentScale = contentScale,
                placeholder = painterResource(id = placeholderResId),
                error = painterResource(id = placeholderResId)
            )
        }
    }
}

// region Previews

@Preview
@Composable
private fun Preview() {
    SmartImage(url = null)
}

// endregion Previews
