package br.com.mvclopes.gistcompose.model.domain

import android.os.Parcelable
import br.com.mvclopes.gistcompose.model.repository.api.GistResponse
import br.com.mvclopes.gistcompose.utils.EMPTY_TEXT
import br.com.mvclopes.gistcompose.utils.orEmptyMap
import br.com.mvclopes.gistcompose.utils.orFalse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Gist(
    val id: String = EMPTY_TEXT,
    val description: String = EMPTY_TEXT,
    val files: @RawValue Map<String, File> = HashMap(),
    val date: String = EMPTY_TEXT,
    val owner: Owner = Owner(),
    var isFavorite: Boolean = false
): Parcelable
