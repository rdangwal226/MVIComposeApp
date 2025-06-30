package com.mvi.compose_animals_app.presentation.features.animal_list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mvi.compose_animals_app.R
import com.mvi.compose_animals_app.core.extensions.estimateSkeletonItemCount
import com.mvi.compose_animals_app.domain.model.Animal
import com.mvi.compose_animals_app.presentation.components.CustomTopAppBar
import com.mvi.compose_animals_app.presentation.features.animal_list.components.AnimalItem
import com.mvi.compose_animals_app.presentation.features.animal_list.components.AnimalItemSkeleton
import com.mvi.compose_animals_app.presentation.features.animal_list.state.AnimalListState

@Composable
fun AnimalListContent(
    uiState: AnimalListState,
    onRetry: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToDetails: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(R.string.animal_list_title),
                onNavigateBack = onNavigateBack
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
                is AnimalListState.Idle -> IdleScreen(onRetry = onRetry)
                is AnimalListState.Loading -> AnimalListSkeleton()
                is AnimalListState.Success -> AnimalList(
                    animalList = uiState.animalList,
                    onItemClick = { animal ->
                        onNavigateToDetails(animal.name)
                    }
                )

                is AnimalListState.Error -> {
                    val context = LocalContext.current
                    val errorMessage = uiState.message ?: stringResource(R.string.unknown_error)

                    IdleScreen(onRetry = onRetry)
                    LaunchedEffect(uiState.message) {
                        Toast.makeText(
                            context,
                            errorMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}

@Composable
private fun IdleScreen(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.fetch_animals))
        }
    }
}

@Composable
private fun AnimalList(
    animalList: List<Animal>,
    onItemClick: (Animal) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(items = animalList, key = { it.name }) { animal ->
            AnimalItem(
                animal = animal,
                onClick = { onItemClick(animal) }
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
}

// region Skeleton

@Composable
private fun AnimalListSkeleton() {
    val configuration = LocalConfiguration.current
    val itemsToShow = configuration.estimateSkeletonItemCount(itemHeightDp = 108)

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(itemsToShow) {
            AnimalItemSkeleton()
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
}

// endregion Skeleton

// region Previews

@Preview
@Composable
private fun SuccessPreview() {
    AnimalListContent(
        uiState = AnimalListState.Success(animalList = Animal.mockList()),
        onRetry = {},
        onNavigateBack = {},
        onNavigateToDetails = {}
    )
}

@Preview
@Composable
private fun IdlePreview() {
    AnimalListContent(
        uiState = AnimalListState.Idle,
        onRetry = {},
        onNavigateBack = {},
        onNavigateToDetails = {}
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    AnimalListContent(
        uiState = AnimalListState.Loading,
        onRetry = {},
        onNavigateBack = {},
        onNavigateToDetails = {}
    )
}

// endregion Previews
