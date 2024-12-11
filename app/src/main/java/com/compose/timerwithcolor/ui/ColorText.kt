package com.compose.timerwithcolor.ui

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
fun ColorText(modifier: Modifier = Modifier, input: String, textColor: Color) {

    val font = FontFamily(
        Font(R.font.roboto_regular)
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        style = TextStyle(
            fontFamily = font,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        ),
        text = input,
        color = textColor,
    )
}