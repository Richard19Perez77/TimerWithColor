package com.compose.timerwithcolor.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.timerwithcolor.R

@Composable
fun CurrentTime(textColor: Color, tempColor: String, hasDarkness: String, text: String) {
    val font = FontFamily(
        Font(R.font.roboto_regular)
    )

    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            style = TextStyle(
                fontFamily = font,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            ),
            text = tempColor,
            color = textColor,
        )

        FitText(Modifier, text, textColor)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            style = TextStyle(
                fontFamily = font,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            ),
            text = hasDarkness,
            color = textColor,
        )
    }
}
