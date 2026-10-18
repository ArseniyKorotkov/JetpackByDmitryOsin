package by.arsy.jetpackbydmitryosin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrencyConverter(
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CurrencyConverter(
    modifier: Modifier = Modifier
) {
    var current by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        TextField(
            value = current,
            onValueChange = { current = it },
            prefix = { Text(text = "RUB:") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        val usd = current.toDoubleOrNull()?.div(90.0) ?: 0.0
        Text(text = "USD: ${round(usd * 100) / 100}")
    }

}

@Preview
@Composable
private fun CurrencyConverterPreview() {
    JetpackByDmitryOsinTheme {
        CurrencyConverter()
    }
}