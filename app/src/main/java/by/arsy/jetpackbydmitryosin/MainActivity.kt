package by.arsy.jetpackbydmitryosin

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                Scaffold { paddingValues ->
                   ComposableTextView(
                       massage = "Hello",
                       modifier = Modifier.padding(paddingValues)
                   )
                }
            }
        }
    }
}

@Composable
fun ComposableTextView(
    massage: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { content ->
            TextView(content)
        },
        update = { textView ->
            textView.apply { text = massage }
        }

    )
}