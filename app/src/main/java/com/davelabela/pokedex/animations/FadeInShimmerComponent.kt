package com.davelabela.pokedex.animations

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

@Composable
fun FadeInShimmerComponent(
    shimmer: @Composable () -> Unit,
    content: @Composable () -> Unit,
    isLoading: Boolean,
) = Box {
    FadeInComponent(visible = isLoading) { shimmer() }
    FadeInComponent(visible = !isLoading) { content() }
}