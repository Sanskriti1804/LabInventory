package com.example.labinventory.data.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Categories for responsiveness
enum class WindowType { Compact, Medium, Expanded }

// Holder for responsive data
data class WindowSizeClass(val windowType: WindowType)

// Provide a CompositionLocal so any Composable can read it
val LocalWindowSizeClass = staticCompositionLocalOf {
    WindowSizeClass(WindowType.Compact)
}

// Utility to calculate current window type
@Composable
fun rememberWindowSizeClass(): WindowSizeClass {
    val config = LocalConfiguration.current
    val width = config.screenWidthDp

    val type = when {
        width < 600 -> WindowType.Compact    // phones portrait
        width < 840 -> WindowType.Medium     // phones landscape / small tablets
        else -> WindowType.Expanded          // tablets
    }

    return WindowSizeClass(type)
}
