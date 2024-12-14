package com.compose.timerwithcolor

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.compose.timerwithcolor.ui.Section
import com.compose.timerwithcolor.ui.theme.TimerWithColorTheme

class MainActivity : BaseActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerWithColorTheme {
                var id = 0
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            testTagsAsResourceId = true
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .testTag("rootRow")
                    ) {
                        Section(id++)
                    }
//                    Row(
//                        modifier = Modifier
//                            .weight(1f)
//                    ) {
//                        Section(id++)
//                    }
                }
            }
        }
    }
}