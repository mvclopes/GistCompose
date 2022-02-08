package br.com.mvclopes.gistcompose.view.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import br.com.mvclopes.gistcompose.R
import br.com.mvclopes.gistcompose.utils.ROUTE_HOME
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    /*TODO: aperfeiçoar SplashScreen
        - Remover appBar superior e inferior durante sua exbição
        - Não permitir voltar a tela de SplashScreen depois de ser exibida
        - (?) Iniciar requisição dos dados a API durante sua exibição
     */
    val scale = remember {
        Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(ROUTE_HOME)
    }

    // Image
    Surface(color = Color(0xFFB9B9B9), modifier = Modifier.fillMaxSize()){
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.logo_gist_escrito),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value))
        }
    }
}
