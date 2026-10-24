package by.arsy.jetpackbydmitryosin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                Scaffold { paddingValues ->
                    Navigation(
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
fun Navigation(modifier: Modifier = Modifier) {
    val loginDestination = "login"
    val userDestination = "user"
    val userName = "name"
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = loginDestination
    ) {
        composable(
            route = loginDestination
        ) {
            LoginScreen(
                onLogin = { navController.navigate(route = "$userDestination/$it") },
                modifier = modifier
            )
        }
        composable(
            route = "$userDestination/{$userName}"
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString(userName)?.let {
                UserScreen(
                    name = it,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLogin: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isNameError by rememberSaveable { mutableStateOf(value = false) }
    var name by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TextField(
            value = name,
            onValueChange = {
                name = it
                isNameError = false
            },
            placeholder = {
                Text(text = if (isNameError) "Name can not be empty!" else "Enter your name")
            },
            isError = isNameError,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (name.isBlank()) {
                    isNameError = true
                } else {
                    onLogin(name)
                }
            }
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun UserScreen(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello, $name!",
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}