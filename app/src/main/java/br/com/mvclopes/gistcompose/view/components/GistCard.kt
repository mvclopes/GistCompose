package br.com.mvclopes.gistcompose.view.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.mvclopes.gistcompose.R
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.ui.theme.GrayGistItem
import br.com.mvclopes.gistcompose.utils.ROUTE_DETAIL
import br.com.mvclopes.gistcompose.utils.filterFiles
import br.com.mvclopes.gistcompose.utils.tratativeDate
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GistCard(
    gist: Gist,
    navController: NavController
) {
    var isFavorite by remember { mutableStateOf(gist.isFavorite) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                val gistJson = Uri.encode(Gson().toJson(gist))
                navController.navigate("$ROUTE_DETAIL/$gistJson")
           },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = GrayGistItem
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GlideImage(
                //TODO: adicionar placeholder para carregamento e erro
                imageModel = gist.owner.avatarUrl,
                contentDescription = "Gist user photo",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
                    .border(width = 4.dp, color = Color.White, shape = CircleShape)

            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Created at: ${tratativeDate(gist.date)}",
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    val filesLanguage = filterFiles(gist.files.values)
                    if (filesLanguage.size > 0){
                        Text(
                            text = "Languages:",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            mainAxisSize = SizeMode.Wrap,
                            mainAxisSpacing = 5.dp,
                            crossAxisSpacing = 5.dp
                        ) {
                            filesLanguage.forEach { language ->
                                Text(
                                    text = language,
                                    fontSize = 12.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .background(Color.Red, RoundedCornerShape(5.dp))
                                        .padding(5.dp)
                                )
                            }
                        }
                    }else {
                        Text(
                            text = "No specified language used in this Gist",
                            fontSize = 14.sp,
                            color = Color.White,
                        )
                    }

                }

            }

            Image(
                painter = painterResource(
                    id = if (isFavorite) R.drawable.ic_favorite_full
                    else R.drawable.ic_favorite_border
                ),
                contentDescription = "Favorite icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        isFavorite = !isFavorite
                        gist.isFavorite = !gist.isFavorite
                    }
                    .align(Alignment.CenterVertically)
            )
        }
    }
}
