package com.example.labinventory.data.model

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class Spacing(
    val tiny: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
)

val LocalSpacing = staticCompositionLocalOf { CompactSpacing }

// Spacing presets
val CompactSpacing = Spacing(2.dp, 4.dp, 8.dp, 12.dp, 16.dp)
val MediumSpacing = Spacing(4.dp, 8.dp, 16.dp, 24.dp, 32.dp)
val ExpandedSpacing = Spacing(8.dp, 16.dp, 24.dp, 32.dp, 48.dp)