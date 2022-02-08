package br.com.mvclopes.gistcompose.model.repository

import br.com.mvclopes.gistcompose.model.domain.Gist

interface Repository {
    suspend fun fetchGists(): List<Gist>
    suspend fun fetchBannerImage(): String
}