package com.mvi.compose_animals_app.presentation.features.animal_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.compose_animals_app.R
import com.mvi.compose_animals_app.core.ResourceProvider
import com.mvi.compose_animals_app.core.extensions.dataOrEmptyList
import com.mvi.compose_animals_app.core.extensions.exceptionOrNull
import com.mvi.compose_animals_app.core.extensions.isFailure
import com.mvi.compose_animals_app.core.extensions.isLoading
import com.mvi.compose_animals_app.core.extensions.isSuccess
import com.mvi.compose_animals_app.domain.usecase.GetAnimalsUseCase
import com.mvi.compose_animals_app.presentation.features.animal_list.intent.AnimalListIntent
import com.mvi.compose_animals_app.presentation.features.animal_list.state.AnimalListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalListViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val getAnimalsUseCase: GetAnimalsUseCase
) : ViewModel() {
    // region Intents

    val uiIntentChannel = Channel<AnimalListIntent>(Channel.BUFFERED)

    // endregion Intents

    // region State

    private val _state = mutableStateOf<AnimalListState>(AnimalListState.Idle)
    val state: State<AnimalListState> = _state

    // endregion State

    // region Lifecycle

    fun onInit() {
        viewModelScope.launch {
            collectIntents()
        }
    }

    // endregion Lifecycle

    // region Intent Collector

    private suspend fun collectIntents() {
        uiIntentChannel.consumeAsFlow()
            .catch { e -> _state.value = AnimalListState.Error(e.message) }
            .collect { intent ->
                when (intent) {
                    is AnimalListIntent.LoadAnimals  -> loadAnimals()
                }
            }
    }

    // endregion Intent Collector

    // region Actions

    private suspend fun loadAnimals() {
        getAnimalsUseCase.invoke().collect { result ->
            _state.value = when {
                result.isLoading -> AnimalListState.Loading
                result.isSuccess -> AnimalListState.Success(result.dataOrEmptyList())
                result.isFailure -> AnimalListState.Error(
                    result.exceptionOrNull?.message
                        ?: resourceProvider.getString(R.string.unknown_error)
                )

                else -> AnimalListState.Idle
            }
        }
    }

    // endregion Actions
}
