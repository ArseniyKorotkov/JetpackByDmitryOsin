package by.arsy.jetpackbydmitryosin

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Photo(
    val id: Int,
    val height: Dp,
    val width: Dp,
    val color: Color
)

fun getPhotoList(): List<Photo> {
    val colors = listOf(
        Color.Gray,
        Color.Cyan,
        Color.White,
        Color.Yellow
    )
    val randomRange = (80..100)
    return List(60) {
        Photo(
            id = it,
            height = randomRange.random().dp,
            width = randomRange.random().dp,
            color = colors[randomRange.random() % colors.size]
        )
    }
}