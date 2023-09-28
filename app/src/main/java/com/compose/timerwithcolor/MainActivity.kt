package com.compose.timerwithcolor

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.timerwithcolor.ui.Section
import com.compose.timerwithcolor.ui.theme.MikuLightGreen
import com.compose.timerwithcolor.ui.theme.TimerWithColorTheme

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerWithColorTheme {
                Surface(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxSize(),
                    color = Color.Black,
                ) {
                    var id = 0
                    Column(modifier = Modifier
                        .padding(0.dp)
                        .fillMaxSize()) {
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .weight(1f)) {
                            Section(id++)
                        }
                        Row(modifier = Modifier
                            .padding(0.dp)
                            .weight(1f)) {
                            Section(id++)
                        }
                    }
                }
            }
        }
    }
}