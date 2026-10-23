package by.arsy.jetpackbydmitryosin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val HOME_DESTINATION = "home_destination"
        const val PROFILE_DESTINATION = "profile_destination"
        const val PROFILE_ID = "profile_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                val viewModel: HomeViewModel = viewModel()
                val uiLoading by viewModel.uiState.collectAsStateWithLifecycle()
                Scaffold { paddingValues ->
                    Navigation(
                        uiLoading = uiLoading,
                        startLoading = { viewModel.startLoading() },
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
fun AppWrapper(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        content()
    }
}

