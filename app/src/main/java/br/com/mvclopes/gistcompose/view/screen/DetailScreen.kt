package br.com.mvclopes.gistcompose.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.utils.DEFAULT_BANNER_IMAGE
import br.com.mvclopes.gistcompose.utils.mockGist
import br.com.mvclopes.gistcompose.view.components.BannerDetail
import br.com.mvclopes.gistcompose.view.components.FileCarousel

@Composable
fun DetailScreen(
    gist: Gist,
    imageBanner: String,
//    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        BannerDetail(gist, imageBanner)
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Description",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            val description = if (gist.description.isBlank()) "This Gist has no description" else gist.description
            Text(
                text = description,
                fontSize = 16.sp
            )

        }
        FileCarousel(files = gist.files.values.toList())
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(mockGist(), DEFAULT_BANNER_IMAGE)
}