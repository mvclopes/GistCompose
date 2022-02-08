package br.com.mvclopes.gistcompose.model.domain

import br.com.mvclopes.gistcompose.utils.EMPTY_TEXT
import br.com.mvclopes.gistcompose.utils.orZero

data class FileResponse(
    val filename: String? = EMPTY_TEXT,
    val type: String? = EMPTY_TEXT,
    val language: String? = EMPTY_TEXT,
    val size: Long? = 0
)

data class File(
    val filename: String,
    val type: String,
    val language: String,
    val size: Long
)

fun FileResponse?.toDomain(): File {
    return File(
        filename = this?.filename.orEmpty(),
        type = this?.type.orEmpty(),
        language = this?.language.orEmpty(),
        size = this?.size.orZero()
    )
}