package br.com.mvclopes.gistcompose.usecases

import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.model.repository.Repository
import br.com.mvclopes.gistcompose.utils.toDomain

class GetGistsUseCase(
    private val repository: Repository
) {
    suspend fun invoke(): List<Gist> {
        return repository.fetchGists().map { it.toDomain() }
    }
}