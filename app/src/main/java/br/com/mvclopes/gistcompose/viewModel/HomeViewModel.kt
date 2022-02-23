package br.com.mvclopes.gistcompose.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.usecases.GetGistsUseCase
import br.com.mvclopes.gistcompose.utils.EMPTY_TEXT
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: GetGistsUseCase
): ViewModel() {

    private var _gists : MutableState<List<Gist>> = mutableStateOf(listOf())
    val gists : MutableState<List<Gist>>
        get() = _gists

    private var _bannerImage : MutableState<String> = mutableStateOf(EMPTY_TEXT)
    val bannerImage : MutableState<String>
        get() = _bannerImage

    init {
        getPublicGists()
    }

    private fun getPublicGists() {
        viewModelScope.launch {
            try {
                _gists.value = useCase.invoke()
            } catch (e: Exception) { Log.i("TAG_ViewModel", "Failure: ${e.message}") }
        }
    }
}
