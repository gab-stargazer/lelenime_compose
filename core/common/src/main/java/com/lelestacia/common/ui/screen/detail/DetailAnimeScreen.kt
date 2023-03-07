package com.lelestacia.common.ui.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DetailAnimeScreen(
    modifier: Modifier = Modifier,
    animeID: Int,
    snackbarHostState: SnackbarHostState
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier.verticalScroll(state = scrollState)
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            
        }
    }
}