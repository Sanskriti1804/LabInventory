package com.example.labinventory.util

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.labinventory.data.model.ResponsiveLayout
import com.example.labinventory.data.model.ScreenSize
import com.example.labinventory.data.remote.LocalResponsiveLayout

@Composable
fun ResponsiveAppWrapper(
    content: @Composable () -> Unit
) {
    // Gives access to screen dimensions
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        with(this) {
            val screenWidth = maxWidth
            val screenHeight = maxHeight

            // Get current device config - screen orientation, font scale, etc
            val configuration = LocalConfiguration.current

            // Determine screen size category
            val screenSize = when {
                screenWidth < 360.dp -> ScreenSize.SMALL_PHONE
                screenWidth < 480.dp -> ScreenSize.MEDIUM_PHONE
                screenWidth < 600.dp -> ScreenSize.LARGE_PHONE
                screenWidth < 840.dp -> ScreenSize.SMALL_TABLET
                screenWidth < 1200.dp -> ScreenSize.LARGE_TABLET
                else -> ScreenSize.DESKTOP
            }

            // Instance of ResponsiveLayout
            val layoutInfo = ResponsiveLayout(
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                topPadding = when (screenSize) {
                    ScreenSize.SMALL_PHONE -> 8.dp
                    ScreenSize.MEDIUM_PHONE -> 12.dp
                    ScreenSize.LARGE_PHONE -> 16.dp
                    ScreenSize.SMALL_TABLET -> 20.dp
                    ScreenSize.LARGE_TABLET -> 24.dp
                    ScreenSize.DESKTOP -> 32.dp
                },
                isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT,
                screenSize = screenSize
            )

            // It shares the layout info with everything inside the app screen so any composable can use it
            CompositionLocalProvider(LocalResponsiveLayout provides layoutInfo) {
                content()
            }
        }
    }
}
