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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.Instant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                Scaffold { paddingValues ->
                    Timer(
                        modifier = Modifier
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
    var timer by rememberSaveable { mutableLongStateOf(value = 0) }
    val roundedTimer by remember { derivedStateOf { (timer / 100) * 100 } }
    var runTime by rememberSaveable { mutableLongStateOf(value = 0) }
    var isRunning by rememberSaveable { mutableStateOf(value = false) }

    LaunchedEffect(key1 = isRunning) {
        while (isRunning) {
            timer = Instant.now().toEpochMilli() - runTime
            delay(timeMillis = 10)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Time(milliAll = roundedTimer)
        Button(
            onClick = {
                if (timer == 0L) {
                    runTime = Instant.now().toEpochMilli()
                }
                isRunning = !isRunning
            }
        ) {
            Text(text = if (isRunning) "pause" else "run")
        }
        Button(
            onClick = {
                isRunning = false
                timer = 0
            }
        ) {
            Text(text = "stop")
        }
    }
}

@Composable
fun Time(
    milliAll: Long,
    modifier: Modifier = Modifier
) {
    val duration = Duration.ofMillis(milliAll)
    val millisTotal = duration.toMillis()
    val millis = (millisTotal % 1000).toStringWithMinimumLength(3)
    val seconds = (millisTotal / 1000 % 60).toStringWithMinimumLength(2)
    val minutes = (duration.toMinutes() % 60).toStringWithMinimumLength(2)
    val hours = (duration.toHours() % 24).toStringWithMinimumLength(2)
    val days = duration.toDays()

    Text(
        text = "$days:$hours:$minutes:$seconds:$millis",
        modifier = modifier
    )
}

fun Long.toStringWithMinimumLength(length: Int): String {
    val builder = StringBuilder(this.toString())
    while (builder.length < length) {
        builder.insert(0, "0")
    }
    return builder.toString()
}
