package by.arsy.jetpackbydmitryosin

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Buttons(
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        repeat(times = 6) {
            Button(
                onClick = { onClick(it) }
            ) {
                Text(text = it.toString())
            }
        }
    }
}

@Composable
fun Profile(
    id: Int,
    uiLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val text = if (uiLoading) "Loading..." else id
    Text(
        text = "profile page#$text",
        modifier = modifier
    )
}