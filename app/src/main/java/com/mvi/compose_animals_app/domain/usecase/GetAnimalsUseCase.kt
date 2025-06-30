package com.mvi.compose_animals_app.domain.usecase

import com.mvi.compose_animals_app.core.result.DataResult
import com.mvi.compose_animals_app.domain.model.Animal
import com.mvi.compose_animals_app.domain.repository.AnimalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetAnimalsUseCase {
    suspend operator fun invoke(): Flow<DataResult<List<Animal>>>
}

class GetAnimalsUseCaseImpl @Inject constructor(
    private val repository: AnimalRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetAnimalsUseCase {
    override suspend fun invoke(): Flow<DataResult<List<Animal>>> = flow {
        emit(DataResult.Loading)
        try {
            emit(DataResult.Success(repository.getAnimals()))
        } catch (e: Exception) {
            emit(DataResult.Failure(e))
        }
    }.flowOn(dispatcher)
}
