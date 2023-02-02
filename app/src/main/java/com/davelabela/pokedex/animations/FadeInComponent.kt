package com.davelabela.pokedex.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable

private const val ANIMATION_DURATION_MILLIS = 100

@Composable
fun FadeInComponent(visible: Boolean, component: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(durationMillis = ANIMATION_DURATION_MILLIS),
            initialAlpha = 0.3F
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = ANIMATION_DURATION_MILLIS),
            targetAlpha = 1f
        )
    ) {
        component()
    }
}