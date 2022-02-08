package br.com.mvclopes.gistcompose.model.domain

import android.os.Parcelable
import br.com.mvclopes.gistcompose.utils.EMPTY_TEXT
import br.com.mvclopes.gistcompose.utils.orZero
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class OwnerResponse(
    val id: Long? = 0,
    val login: String? = EMPTY_TEXT,
    @SerializedName("avatar_url") val avatarUrl: String? = EMPTY_TEXT,
    val url: String? = EMPTY_TEXT
)

@Parcelize
data class Owner(
    val id: Long = 0L,
    val login: String = EMPTY_TEXT,
    val avatarUrl: String = EMPTY_TEXT,
    val url: String = EMPTY_TEXT
): Parcelable

fun OwnerResponse?.toDomain(): Owner {
    return Owner(
        id = this?.id.orZero(),
        login = this?.login.orEmpty(),
        avatarUrl = this?.avatarUrl.orEmpty(),
        url = this?.url.orEmpty()
    )
}

