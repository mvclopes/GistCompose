package br.com.mvclopes.gistcompose.utils

import android.os.Bundle
import androidx.navigation.NavType
import br.com.mvclopes.gistcompose.model.domain.Gist
import com.google.gson.Gson

/**
 * Esta classe foi criada para navegação entre telas e passagem
 * de objetos `parcelable` entre elas.
 *
 */
class AssetParamType: NavType<Gist>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Gist? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Gist {
        return Gson().fromJson(value, Gist::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Gist) {
        bundle.putParcelable(key, value)
    }
}