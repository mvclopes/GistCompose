package br.com.mvclopes.gistcompose.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mvclopes.gistcompose.R
import br.com.mvclopes.gistcompose.model.domain.Gist
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BannerDetail(
    gist: Gist,
    urlImageBanner: String
) {
    var isFavorite by remember { mutableStateOf(gist.isFavorite) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        GlideImage(
            imageModel = urlImageBanner,
            contentDescription = "Cover image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                imageModel = gist.owner.avatarUrl,
                contentDescription = "User image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(width = 4.dp, color = Color.White, shape = CircleShape)
                    .clickable {
                    }
            )

            Text(
                text = gist.owner.login,
                color = Color.White,
                fontSize = 24.sp,
            )
        }

        Image(
            painter = painterResource(
                id = if (isFavorite) R.drawable.ic_favorite_full
                else R.drawable.ic_favorite_border
            ),
            contentDescription = "Favorite icon",
            modifier = Modifier
                .size(60.dp)
                .padding(top = 16.dp, end = 16.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    isFavorite = !isFavorite
                    gist.isFavorite = !gist.isFavorite
                }
        )

    }
}
