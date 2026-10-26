package by.arsy.jetpackbydmitryosin


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HeartImage(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    strokeColor: Color = Color.Black
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val radius = canvasWidth / 4

        val apply = Path().apply {
            moveTo(x = canvasWidth, y = canvasHeight / 4)
            lineTo(x = canvasWidth / 2, y = canvasHeight)
            lineTo(x = 0f, y = canvasHeight / 4)
            arcTo(
                rect = Rect(
                    center = Offset(x = radius, y = canvasHeight / 4),
                    radius = radius
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(
                    center = Offset(x = radius * 3, y = canvasHeight / 4),
                    radius = radius
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )

            close()
        }

        drawPath(
            path = apply,
            color = color
        )
        drawPath(
            path = apply,
            color = strokeColor,
            style = Stroke(width = 2f)
        )
    }
}

@Preview
@Composable
private fun HeartImagePreview() {
    HeartImage(modifier = Modifier.size(100.dp))
}