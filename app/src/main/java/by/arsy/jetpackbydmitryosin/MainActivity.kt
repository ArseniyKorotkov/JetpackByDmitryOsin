package by.arsy.jetpackbydmitryosin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme
import kotlin.math.roundToInt

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
    var animatedText by rememberSaveable { mutableStateOf(value = "Animated text") }
    var offsetX by rememberSaveable { mutableFloatStateOf(value = 0f) }
    var offsetY by rememberSaveable { mutableFloatStateOf(value = 0f) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var state by rememberSaveable { mutableStateOf(value = true) }

            AnimatedContent(
                targetState = state,
                transitionSpec = {
                    slideInVertically(
                        animationSpec = spring(dampingRatio = 0.18f)
                    ) { -it } + fadeIn() togetherWith fadeOut(
                        animationSpec = tween(durationMillis = 800)
                    )
                }
            ) { targetState ->
                Text(
                    text = "$animatedText $targetState",
                )
            }

            Button(
                onClick = { state = !state }
            ) {
                Text(text = "Change")
            }

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = offsetX.roundToInt(),
                            y = offsetY.roundToInt()
                        )
                    }
                    .pointerInput(key1 = Unit) {
                        detectTapGestures(
                            onTap = { animatedText = "Tap" },
                            onDoubleTap = { animatedText = "Double Tap" },
                            onLongPress = { animatedText = "Long press" }
                        )
                    }
                    .pointerInput(key1 = Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            animatedText = "Drag press"
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            ) {
                Text("Change text")
            }
        }
    }
}