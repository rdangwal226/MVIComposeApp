package com.mvi.compose_animals_app.domain.usecase

import com.mvi.compose_animals_app.core.result.DataResult
import com.mvi.compose_animals_app.domain.model.AnimalDetails
import com.mvi.compose_animals_app.domain.repository.AnimalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetAnimalByNameUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<AnimalDetails?>>
    data class Params(val name: String)
}

class GetAnimalByNameUseCaseImpl @Inject constructor(
    private val repository: AnimalRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetAnimalByNameUseCase {
    override suspend fun invoke(params: GetAnimalByNameUseCase.Params): Flow<DataResult<AnimalDetails?>> =
        flow {
            emit(DataResult.Loading)
            try {
                emit(DataResult.Success(repository.getAnimalByName(params.name)))
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(dispatcher)
}
