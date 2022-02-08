package br.com.mvclopes.gistcompose.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mvclopes.gistcompose.R
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.view.components.GistCard
import br.com.mvclopes.gistcompose.view.components.SearchBar

@Composable
fun HomeScreen(
    gists: List<Gist>,
    navController: NavController
){
    Surface(color = Color(0xFFB9B9B9), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_gist_escrito),
                contentDescription = "Gist logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            SearchBar(
                hint = "Search...",
            ) {
                //TODO: implementar busca de gist na API
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = gists) { gist ->
                    GistCard(gist = gist, navController = navController)
                }
            }
        }
    }
}
