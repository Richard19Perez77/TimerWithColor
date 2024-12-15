package com.compose.timerwithcolor

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.compose.timerwithcolor.ui.Section
import com.compose.timerwithcolor.ui.theme.TimerWithColorTheme

/**
 * The MainActivity class holds the container for the Section composable. It can be extended to hold multiple Sections, as long as the id is separate it will update accordingly. Id is used for testing an finding specific composable.
 */
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