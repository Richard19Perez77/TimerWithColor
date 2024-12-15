package com.compose.timerwithcolor.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.compose.timerwithcolor.ui.model.SectionState
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun Section(id: Int) {
    val delayTime = 16L
    val pattern = "HH:mm:ss.SSS"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val sectionState = remember {
        SectionState(id)
    }

    LaunchedEffect(Unit) {
        while (true) {
            sectionState.time.value = LocalDateTime.now()
            if (sectionState.isFlashing.value) sectionState.colorUpdate()
            delay(delayTime)
        }
    }

    Box(
        modifier = Modifier
            .testTag("Section{$id}Box")
            .semantics {
                testTag = "Section{$id}Box"
                testTagsAsResourceId = true
            }
            .fillMaxSize()
            .background(
                sectionState.bgColor.value
            )
            .combinedClickable(
                onClick = {
                    sectionState.toggleColorFlash()
                },
                onLongClick = {
                    sectionState.toggleAddBlack()
                },
                onDoubleClick = {
                    sectionState.rotateMode()
                },
            ),
        contentAlignment = Alignment.Center
    ) {
        TextReadout(
            id,
            sectionState.textColor.value,
            sectionState.displayName,
            sectionState.getDarknessString(),
            sectionState.time.value.format(formatter)
        )
    }
}