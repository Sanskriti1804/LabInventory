package com.example.labinventory.data.model

import androidx.compose.ui.unit.Dp

data class ResponsiveLayout(
    val screenWidth: Dp,
    val screenHeight: Dp,
    val topPadding: Dp,
    val isPortrait: Boolean,
    val screenSize: ScreenSize,
    val isLandscape: Boolean = !isPortrait
) {
    val isPhone: Boolean
        get() = screenSize in listOf(ScreenSize.SMALL_PHONE, ScreenSize.MEDIUM_PHONE, ScreenSize.LARGE_PHONE)
    
    val isTablet: Boolean
        get() = screenSize in listOf(ScreenSize.SMALL_TABLET, ScreenSize.LARGE_TABLET)
    
    val isDesktop: Boolean
        get() = screenSize == ScreenSize.DESKTOP
}

enum class ScreenSize {
    SMALL_PHONE,      // < 360dp
    MEDIUM_PHONE,     // 360dp - 480dp
    LARGE_PHONE,      // 480dp - 600dp
    SMALL_TABLET,     // 600dp - 840dp
    LARGE_TABLET,     // 840dp - 1200dp
    DESKTOP           // > 1200dp
}