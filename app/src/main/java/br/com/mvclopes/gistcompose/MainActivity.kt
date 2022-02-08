package br.com.mvclopes.gistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.model.repository.RepositoryImp
import br.com.mvclopes.gistcompose.model.repository.api.ApiModule
import br.com.mvclopes.gistcompose.ui.theme.GistComposeTheme
import br.com.mvclopes.gistcompose.utils.*
import br.com.mvclopes.gistcompose.view.screen.DetailScreen
import br.com.mvclopes.gistcompose.view.screen.HomeScreen
import br.com.mvclopes.gistcompose.view.screen.SplashScreen
import br.com.mvclopes.gistcompose.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
//    private val viewModel by viewModels<HomeViewModel>()
    private val viewModel by viewModel<HomeViewModel>()
    /*private val viewModel by lazy {
        HomeViewModel(
            RepositoryImp(
                ApiModule.apiNetwork,
                Dispatchers.IO
            )
        )
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GistComposeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = SPLASH_SCREEN
                ) {
                    composable(SPLASH_SCREEN) {
                        SplashScreen(navController = navController)
                    }
                    composable(ROUTE_HOME) {
                        HomeScreen(gists = viewModel.gists.value, navController = navController)
                    }
                    composable(
                        route = "$ROUTE_DETAIL/{$KEY_GIST}",
                        arguments = listOf(navArgument(KEY_GIST) { type = AssetParamType()})
                    ) {
                        val gist = it.arguments?.getParcelable(KEY_GIST) ?: Gist()
                        DetailScreen(gist = gist, imageBanner = viewModel.bannerImage.value/*, navController = navController*/)
                    }
                }
            }
        }
    }
}
