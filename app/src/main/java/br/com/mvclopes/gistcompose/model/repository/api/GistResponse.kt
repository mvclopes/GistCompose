package br.com.mvclopes.gistcompose.model.repository.api

import br.com.mvclopes.gistcompose.model.domain.FileResponse
import br.com.mvclopes.gistcompose.model.domain.OwnerResponse
import br.com.mvclopes.gistcompose.utils.EMPTY_TEXT
import com.google.gson.annotations.SerializedName

data class GistResponse(
    val id: String? = EMPTY_TEXT,
    val description: String? = EMPTY_TEXT,
    val files: Map<String?, FileResponse?> = HashMap(),
    @SerializedName("created_at") val date: String? = EMPTY_TEXT,
    val owner: OwnerResponse? = OwnerResponse(),
    var isFavorite: Boolean = false
)
