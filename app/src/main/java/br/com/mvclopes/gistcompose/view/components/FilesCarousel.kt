package br.com.mvclopes.gistcompose.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mvclopes.gistcompose.model.domain.File
import br.com.mvclopes.gistcompose.ui.theme.GrayGistItem
import br.com.mvclopes.gistcompose.utils.mockFile
import br.com.mvclopes.gistcompose.utils.mockListFiles
import br.com.mvclopes.gistcompose.utils.tratativeFileSize

@Composable
fun FileCarousel(files: List<File>) {
    if (files.size > 1) {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = "Files",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                items(items = files) { file ->
                    FileCard(file = file)
                }
            }
        }
    }else {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = "File",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            UnitFileCard(files[0])
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun FileCarouselPreview() {
    Column {
        FileCarousel(files = mockListFiles())
        Spacer(modifier = Modifier.size(20.dp))
        FileCarousel(files = listOf( mockListFiles()[0] ))
    }
}