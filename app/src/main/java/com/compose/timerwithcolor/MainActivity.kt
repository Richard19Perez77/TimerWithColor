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
 * MainActivity serves as the entry point of the application and provides the UI layout
 * for the main screen using Jetpack Compose.
 *
 * The activity sets up a themed container (`TimerWithColorTheme`) and organizes its
 * composable content using a `Column` layout. Each row within the column represents a
 * `Section` composable, identified by a unique ID. This setup ensures that multiple
 * sections can be added and updated dynamically while supporting testing capabilities.
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