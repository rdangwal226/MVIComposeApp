package com.mvi.compose_animals_app.presentation.features.animal_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mvi.compose_animals_app.R
import com.mvi.compose_animals_app.core.ApiUtil
import com.mvi.compose_animals_app.core.extensions.shimmer
import com.mvi.compose_animals_app.domain.model.AnimalDetails
import com.mvi.compose_animals_app.presentation.components.CustomTopAppBar
import com.mvi.compose_animals_app.presentation.components.ErrorContent
import com.mvi.compose_animals_app.presentation.components.InfoRow
import com.mvi.compose_animals_app.presentation.components.InfoRowSkeleton
import com.mvi.compose_animals_app.presentation.components.SmartImage
import com.mvi.compose_animals_app.presentation.features.animal_details.state.AnimalDetailsState

@Composable
fun AnimalDetailsContent(
    uiState: AnimalDetailsState,
    onRetry: () -> Unit,
    onNavigationBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(R.string.animal_details_title),
                onNavigateBack = onNavigationBack
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.White
        ) {
            when (uiState) {
                is AnimalDetailsState.Loading -> AnimalDetailsSkeleton()
                is AnimalDetailsState.Success -> AnimalDetails(uiState.animalDetails)
                is AnimalDetailsState.Error -> ErrorContent(
                    message = uiState.message,
                    onRetry = onRetry
                )
            }
        }
    }
}

@Composable
private fun AnimalDetails(animal: AnimalDetails) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        val url = ApiUtil.BASE_URL + animal.image

        SmartImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            url = url,
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = animal.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoRow(
            title = stringResource(R.string.location),
            value = animal.location,
            icon = Icons.Default.LocationOn
        )
        InfoRow(
            title = stringResource(R.string.diet),
            value = animal.diet,
            icon = Icons.Filled.Restaurant
        )
        InfoRow(
            title = stringResource(R.string.lifespan),
            value = animal.lifespan,
            icon = Icons.Filled.HourglassBottom
        )

        animal.taxonomy?.let {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.taxonomy),
                style = MaterialTheme.typography.titleMedium
            )
            InfoRow(
                title = stringResource(R.string.kingdom),
                value = it.kingdom,
                icon = Icons.Filled.AccountTree
            )
            InfoRow(
                title = stringResource(R.string.order),
                value = it.order,
                icon = Icons.Filled.Category
            )
            InfoRow(
                title = stringResource(R.string.family),
                value = it.family,
                icon = Icons.Filled.Group
            )
        }

        animal.speed?.let {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.speed),
                style = MaterialTheme.typography.titleMedium
            )
            InfoRow(
                title = stringResource(R.string.metric),
                value = it.metric.ifBlank { stringResource(R.string.n_a) },
                icon = Icons.Filled.Speed
            )
            InfoRow(
                title = stringResource(R.string.imperial),
                value = it.imperial.ifBlank { stringResource(R.string.n_a) },
                icon = Icons.Filled.Speed
            )
        }
    }
}

// region Skeleton

@Composable
fun AnimalDetailsSkeleton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .shimmer()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.5f)
                .height(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmer()
        )

        Spacer(modifier = Modifier.height(16.dp))

        repeat(3) {
            InfoRowSkeleton()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmer()
        )

        repeat(3) {
            InfoRowSkeleton()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmer()
        )

        repeat(2) {
            InfoRowSkeleton()
        }
    }
}

// endregion Skeleton

// region Previews

@Preview
@Composable
private fun SuccessPreview() {
    AnimalDetailsContent(
        uiState = AnimalDetailsState.Success(animalDetails = AnimalDetails.mock()),
        onRetry = {},
        onNavigationBack = {}
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    AnimalDetailsContent(
        uiState = AnimalDetailsState.Loading,
        onRetry = {},
        onNavigationBack = {}
    )
}

@Preview
@Composable
private fun ErrorPreview() {
    AnimalDetailsContent(
        uiState = AnimalDetailsState.Error(null),
        onRetry = {},
        onNavigationBack = {}
    )
}

// endregion Previews
