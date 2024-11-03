package com.compose.timerwithcolor.ui

import android.graphics.Paint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.timerwithcolor.R

@Composable
fun FitText(modifier: Modifier = Modifier, input: String, textColor: Color) {
    var textSizeState = remember { mutableFloatStateOf(1.0f) }
    var fontScaleSet = remember { mutableStateOf(false) }
    val localDensity = LocalDensity.current

    val font = FontFamily(
        Font(R.font.roboto_regular)
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .onSizeChanged { it ->
                if (!fontScaleSet.value) {
                    var columnWidthDp = with(localDensity) { it.width.toDp() }
                    var result: Dp = Paint()
                        .apply {
                            textSize = textSizeState.floatValue
                        }
                        .measureText(input).dp

                    while (result < columnWidthDp) {
                        textSizeState.floatValue = textSizeState.floatValue.inc()
                        result = Paint()
                            .apply {
                                textSize = textSizeState.floatValue
                            }
                            .measureText(input).dp
                    }

                    textSizeState.floatValue = textSizeState.floatValue.dec()
                    fontScaleSet.value = true
                }
            },
        style = TextStyle(
            fontFamily = font,
            textAlign = TextAlign.Center,
        ),
        fontSize = textSizeState.floatValue.sp,
        text = input,
        color = textColor,
    )
}