package br.com.mvclopes.gistcompose.model.repository

import android.util.Log
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.model.domain.toDomain
import br.com.mvclopes.gistcompose.model.repository.api.ApiModule
import br.com.mvclopes.gistcompose.model.repository.api.ApiService
import br.com.mvclopes.gistcompose.utils.BANNER_IMG_URL
import br.com.mvclopes.gistcompose.utils.DEFAULT_BANNER_IMAGE
import br.com.mvclopes.gistcompose.utils.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImp(
    private val service: ApiService,
    private val dispatcher: CoroutineDispatcher
): Repository {

    override suspend fun fetchGists(): List<Gist> {
        var result: List<Gist> = ArrayList()
        withContext(dispatcher){
            try {
                result = service.getPublicGists().map { it.toDomain() }
            } catch (e: Exception) { Log.i("TAG_Repo", "Failure: ${e.message}") }
        }
        return result
    }

    //TODO: entender como tratar endpoints que redirecionam a outro endpoint
    override suspend fun fetchBannerImage(): String {
        var result: String
        withContext(dispatcher) {
            result = try {
                val response = service.getBannerImage(BANNER_IMG_URL)
                Log.i("TAG_Repo", "response: $response")
                DEFAULT_BANNER_IMAGE
            } catch (e: Exception) {
                Log.i("TAG_Repo", "Failure: ${e.message}")
                DEFAULT_BANNER_IMAGE
            }
        }
        return result
    }

}