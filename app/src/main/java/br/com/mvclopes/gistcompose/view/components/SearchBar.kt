package br.com.mvclopes.gistcompose.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mvclopes.gistcompose.utils.EMPTY_TEXT

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search bar",
    onSearch: (String) -> Unit = {}
) {
    var searchField by remember { mutableStateOf(EMPTY_TEXT) }
    var isHintDisplayed by remember { mutableStateOf( hint != EMPTY_TEXT) }

    Box(modifier = modifier) {
        BasicTextField(
            value = searchField,
            onValueChange = {
                searchField = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White, shape = CircleShape)
                .border(2.dp, Color.Black, CircleShape)
                .shadow(5.dp, shape = CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                },
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                fontSize = 18.sp
            )
        }
    }
}
