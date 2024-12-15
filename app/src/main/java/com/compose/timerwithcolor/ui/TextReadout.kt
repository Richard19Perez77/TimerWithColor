package com.compose.timerwithcolor.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.timerwithcolor.R

@Composable
fun TextReadout(id: Int, textColor: Color, tempColor: String, hasDarkness: String, text: String) {

    val font = FontFamily(
        Font(R.font.roboto_regular)
    )

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            modifier = Modifier
                .testTag("Temp{$id}Text")
                .padding(bottom = 28.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontFamily = font,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            ),
            text = tempColor,
            color = textColor,
        )

        Text(
            modifier = Modifier
                .testTag("Timer{$id}Text")
                .fillMaxWidth(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = font,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
            ),
            text = text,
            color = textColor,
        )

        Text(
            modifier = Modifier
                .testTag("Darkness{$id}Text")
                .padding(top = 28.dp)
                .fillMaxWidth(),
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
