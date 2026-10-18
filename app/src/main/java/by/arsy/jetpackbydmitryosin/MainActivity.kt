package by.arsy.jetpackbydmitryosin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StyledCard(
                        name = "Dmitry",
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun StyledCard(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        modifier = modifier
            .width(200.dp)
            .padding(all = 16.dp)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = Color.Cyan)
            .clickable {}
            .padding(all = 8.dp)

    )
}

@Preview
@Composable
private fun StyledCardPreview() {
    JetpackByDmitryOsinTheme {
        StyledCard(
            name = "Dmitry"
        )
    }
}