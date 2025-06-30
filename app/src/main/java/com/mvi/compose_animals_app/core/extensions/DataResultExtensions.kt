package com.mvi.compose_animals_app.core.extensions

import com.mvi.compose_animals_app.core.result.DataResult

val <T> DataResult<T>.isLoading: Boolean
    get() = this is DataResult.Loading

val <T> DataResult<T>.isSuccess: Boolean
    get() = this is DataResult.Success

val <T> DataResult<T>.isFailure: Boolean
    get() = this is DataResult.Failure

val <T> DataResult<T>.dataOrNull: T?
    get() = (this as? DataResult.Success)?.data

fun <T> DataResult<List<T>>.dataOrEmptyList(): List<T> =
    (this as? DataResult.Success)?.data ?: emptyList()

val <T> DataResult<T>.exceptionOrNull: Throwable?
    get() = (this as? DataResult.Failure)?.e
