package com.davelabela.pokedex.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer


fun Modifier.customPlaceholder(visible: Boolean, shape: Shape) = placeholder(
    visible = visible,
    color = Color(0x99000000),
    shape = shape,
    highlight = PlaceholderHighlight.shimmer(
        highlightColor = Color.White,
    )
)