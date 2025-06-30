package com.mvi.compose_animals_app.core.extensions

import kotlinx.coroutines.channels.Channel
import timber.log.Timber

fun <T> Channel<T>.safeSend(value: T, tag: String = "UIIntent") {
    val result = this.trySend(value)
    if (result.isSuccess) {
        Timber.tag(tag).d("Intent sent successfully: $value")
    } else {
        val errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
        Timber.tag(tag).w("Failed to send intent: $value. Error: $errorMessage")
    }
}
