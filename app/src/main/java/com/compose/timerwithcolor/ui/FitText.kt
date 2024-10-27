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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.timerwithcolor.R

@Composable
fun FitText(modifier: Modifier = Modifier, input: String, textColor: Color) {
    var textSizeState = remember { mutableStateOf(0.sp) }
    var fontScaleSet = remember { mutableStateOf(false) }

    val localDensity = LocalDensity.current
    val fontScaleFactor = LocalDensity.current.fontScale
    var floatForScale = remember { mutableFloatStateOf(0f) }

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
                    var fontSize =
                        TextUnit(floatForScale.floatValue * fontScaleFactor, TextUnitType.Sp).value
                    var result: Dp = Paint()
                        .apply {
                            textSize = fontSize
                        }
                        .measureText(input).dp

                    while (result < columnWidthDp) {
                        floatForScale.value += 1f
                        fontSize =
                            TextUnit(
                                floatForScale.floatValue * fontScaleFactor,
                                TextUnitType.Sp
                            ).value
                        result = Paint()
                            .apply {
                                textSize = fontSize
                            }
                            .measureText(input).dp
                    }

                    floatForScale.value -= 7f
                    fontSize =
                        TextUnit(floatForScale.floatValue * fontScaleFactor, TextUnitType.Sp).value
                    result = Paint()
                        .apply {
                            textSize = fontSize
                        }
                        .measureText(input).dp

                    textSizeState.value = fontSize.sp
                    fontScaleSet.value = true
                }
            },
        style = TextStyle(
            fontFamily = font,
            fontSize = textSizeState.value,
            textAlign = TextAlign.Center,
        ),
        text = input,
        color = textColor,
    )
}