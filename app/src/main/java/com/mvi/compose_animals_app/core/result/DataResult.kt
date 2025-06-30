package com.mvi.compose_animals_app.core.result

sealed class DataResult<out T> {
    data object Loading : DataResult<Nothing>()
    data class Success<out T>(val data: T?) : DataResult<T>()
    data class Failure(val e: Exception?) : DataResult<Nothing>()
}
