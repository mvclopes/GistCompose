package br.com.mvclopes.gistcompose.utils

import br.com.mvclopes.gistcompose.model.domain.File
import br.com.mvclopes.gistcompose.model.domain.FileResponse
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.model.domain.toDomain
import br.com.mvclopes.gistcompose.model.repository.api.GistResponse

fun Long?.orZero(): Long = this ?: 0L

fun Boolean?.orFalse(): Boolean = this ?: false

fun Map<String?, FileResponse?>.orEmptyMap(): Map<String, File> {
    return this.mapValues { filesValues ->
        filesValues.value.toDomain()
    }.mapKeys { filesKeys ->
        filesKeys.key.orEmpty()
    }
}

fun GistResponse?.toDomain(): Gist {
    return Gist(
        id = this?.id.orEmpty(),
        description = this?.description.orEmpty(),
        files = this?.files!!.orEmptyMap(), //TODO: Evitar uso de não nulo (!!)
        date = this.date.orEmpty(),
        owner = this.owner.toDomain(),
        isFavorite = this.isFavorite.orFalse()
    )
}