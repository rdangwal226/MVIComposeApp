package com.mvi.compose_animals_app.presentation.features.animal_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.compose_animals_app.R
import com.mvi.compose_animals_app.core.ResourceProvider
import com.mvi.compose_animals_app.core.extensions.dataOrNull
import com.mvi.compose_animals_app.core.extensions.exceptionOrNull
import com.mvi.compose_animals_app.core.extensions.isLoading
import com.mvi.compose_animals_app.core.extensions.isSuccess
import com.mvi.compose_animals_app.core.extensions.safeSend
import com.mvi.compose_animals_app.domain.usecase.GetAnimalByNameUseCase
import com.mvi.compose_animals_app.presentation.features.animal_details.intent.AnimalDetailsIntent
import com.mvi.compose_animals_app.presentation.features.animal_details.state.AnimalDetailsState
import com.mvi.compose_animals_app.presentation.navigation.AnimalDetailsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val resourceProvider: ResourceProvider,
    private val getAnimalByNameUseCase: GetAnimalByNameUseCase
) : ViewModel() {
    // region Arguments

    private val name: String = savedStateHandle[AnimalDetailsArgs.NAME] ?: ""

    // endregion Arguments

    // region Intents

    val uiIntentChannel = Channel<AnimalDetailsIntent>(Channel.BUFFERED)

    // endregion Intents

    // region State

    private val _state = mutableStateOf<AnimalDetailsState>(AnimalDetailsState.Loading)
    val state: State<AnimalDetailsState> = _state

    // endregion State

    // region Lifecycle

    fun onInit() {
        viewModelScope.launch {
            collectIntents()
        }

        uiIntentChannel.safeSend(AnimalDetailsIntent.LoadAnimalDetails)
    }

    // endregion Lifecycle

    // region Intent Collector

    private suspend fun collectIntents() {
        uiIntentChannel.consumeAsFlow()
            .catch { e -> _state.value = AnimalDetailsState.Error(e.message) }
            .collect { intent ->
                when (intent) {
                    is AnimalDetailsIntent.LoadAnimalDetails -> loadAnimalDetails()
                }
            }
    }

    // endregion Intent Collector

    // region Actions

    private suspend fun loadAnimalDetails() {
        getAnimalByNameUseCase.invoke(GetAnimalByNameUseCase.Params(name)).collect { result ->
            _state.value = when {
                result.isLoading -> AnimalDetailsState.Loading
                result.isSuccess -> {
                    result.dataOrNull?.let {
                        AnimalDetailsState.Success(it)
                    } ?: AnimalDetailsState.Error(null)
                }

                else -> AnimalDetailsState.Error(
                    result.exceptionOrNull?.message
                        ?: resourceProvider.getString(R.string.unknown_error)
                )
            }
        }
    }

    // endregion Actions
}
