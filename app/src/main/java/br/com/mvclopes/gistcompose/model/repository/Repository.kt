package br.com.mvclopes.gistcompose.model.repository

import br.com.mvclopes.gistcompose.model.repository.api.GistResponse

interface Repository {
    suspend fun fetchGists(): List<GistResponse>
    suspend fun fetchBannerImage(): String
}