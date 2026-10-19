package by.arsy.jetpackbydmitryosin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PhotoList(
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PhotoList(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 50.dp),
        modifier = modifier.fillMaxWidth()
    ) {

        stickyHeader {
            Text(
                text = "colorful photos#1:",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
        }

        items(
            items = getPhotoList(),
            key = { it.id }
        ) { photo ->
            Box(
                modifier = Modifier
                    .height(photo.height)
                    .width(photo.width)
                    .background(color = photo.color)
            )
        }
        stickyHeader {
            Text(text = "colorful photos#2:")
        }

        items(
            items = getPhotoList(),
            key = { it.id }
        ) { photo ->
            Box(
                modifier = Modifier
                    .height(photo.height)
                    .width(photo.width)
                    .background(color = photo.color)
            )
        }


    }
}

@Preview
@Composable
private fun PhotoListPreview() {
    JetpackByDmitryOsinTheme {
        PhotoList()
    }
}