package by.arsy.jetpackbydmitryosin

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                Scaffold { paddingValues ->
                    Timer(modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                    )
                }
            }
        }
    }
}

@Composable
fun Timer(modifier: Modifier = Modifier) {
    var timer by rememberSaveable { mutableLongStateOf(value = 10) }
    var isRunning by rememberSaveable { mutableStateOf(value = false) }

    LaunchedEffect(key1 = isRunning) {
        while (isRunning && timer > 0) {
            timer--
            delay(timeMillis = 1000)
        }
        isRunning = false
        timer = 10
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = timer.toString())
        Button(
            onClick = { isRunning = true }
        ) {
            Text(text = if (isRunning) "count..." else "run")
        }
    }
}
