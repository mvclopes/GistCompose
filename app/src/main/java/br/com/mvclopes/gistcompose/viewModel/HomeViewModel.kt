package br.com.mvclopes.gistcompose.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.model.domain.toDomain
import br.com.mvclopes.gistcompose.model.repository.Repository
import br.com.mvclopes.gistcompose.model.repository.RepositoryImp
import br.com.mvclopes.gistcompose.utils.EMPTY_TEXT
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: Repository
): ViewModel() {

    private var _gists : MutableState<List<Gist>> = mutableStateOf(listOf())
    val gists : MutableState<List<Gist>>
        get() = _gists

    private var _bannerImage : MutableState<String> = mutableStateOf(EMPTY_TEXT)
    val bannerImage : MutableState<String>
        get() = _bannerImage

    init {
        getPublicGists()
//        getBannerImage()
    }

    private fun getPublicGists() {
        viewModelScope.launch {
            try {
                _gists.value = repo.fetchGists()
            } catch (e: Exception) { Log.i("TAG_ViewModel", "Failure: ${e.message}") }
        }
    }

    private fun getBannerImage() {
        viewModelScope.launch {
            try {
                _bannerImage.value = repo.fetchBannerImage()
                Log.i("TAG_ViewModel", "Banner Image: ${_bannerImage.value}")
            } catch (e: Exception) { Log.i("TAG_ViewModel", "Failure: ${e.message}") }
        }
    }

}
