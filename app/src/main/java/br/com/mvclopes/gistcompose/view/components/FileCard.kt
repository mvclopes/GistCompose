package br.com.mvclopes.gistcompose.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.mvclopes.gistcompose.model.domain.File
import br.com.mvclopes.gistcompose.ui.theme.GrayGistItem
import br.com.mvclopes.gistcompose.utils.getRandomColor
import br.com.mvclopes.gistcompose.utils.mockFile
import br.com.mvclopes.gistcompose.utils.tratativeFileSize

@Composable
fun FileCard(
    file: File,
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(GrayGistItem, Color.Gray)))
                .padding(16.dp)
        ) {
            Text(
                text = file.filename,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            if (file.language.isNotBlank()) {
                Text(
                    text = file.language,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(Color(getRandomColor()), RoundedCornerShape(5.dp))
                        .padding(5.dp)
                )
            }
            Text(
                text = tratativeFileSize(file.size),
                color = Color.White,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomEnd)
            )
        }

    }
}

@Composable
fun UnitFileCard(file: File) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(GrayGistItem, Color.Gray)))
                .padding(16.dp)
        ) {
            Text(
                text = file.filename,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Text(
                text = file.type,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .background(Color(getRandomColor()), RoundedCornerShape(5.dp))
                    .padding(5.dp)
            )
            if (file.language.isNotBlank()) {
                Text(
                    text = file.language,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .background(Color(getRandomColor()), RoundedCornerShape(5.dp))
                        .padding(5.dp)
                )
            }

            Text(
                text = tratativeFileSize(file.size),
                color = Color.White,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FileCardPreview() {
    Column {
        FileCard(file = mockFile())
        Spacer(modifier = Modifier.size(16.dp))
        UnitFileCard(file = mockFile())
    }
}