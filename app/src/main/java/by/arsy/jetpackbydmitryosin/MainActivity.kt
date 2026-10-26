package by.arsy.jetpackbydmitryosin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                Scaffold { paddingValues ->
                    Animation(
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
fun Animation(modifier: Modifier = Modifier) {
    val defaultLikeSize = 50f
    val expandedLikeSize = defaultLikeSize + 15
    val scope = rememberCoroutineScope()

    var isLiked by remember { mutableStateOf(value = false) }

    val likeSize = remember { Animatable( defaultLikeSize) }

    val animateColorAsState by animateColorAsState(
        targetValue = if (isLiked) Color.Red else Color.White,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        HeartImage(
            color = animateColorAsState,
            modifier = Modifier
                .size(likeSize.value.dp)
                .pointerInput(key1 = Unit) {
                    detectTapGestures(
                        onTap = {
                            isLiked = false
                        },
                        onDoubleTap = {
                            isLiked = true
                            scope.launch {
                                likeSize.animateTo(
                                    targetValue = expandedLikeSize,
                                    animationSpec = spring(
                                        dampingRatio = 0.2f,
                                        stiffness = 1200f
                                    )
                                )
                                delay(timeMillis = 150)
                                likeSize.animateTo(
                                    targetValue = defaultLikeSize,
                                    animationSpec = spring(
                                        dampingRatio = 1f,
                                        stiffness = 100f
                                    )
                                )
                            }
                        }
                    )
                }
        )
    }
}