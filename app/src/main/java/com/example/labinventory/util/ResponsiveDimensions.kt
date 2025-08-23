package com.example.labinventory.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.labinventory.data.remote.LocalResponsiveLayout
import com.example.labinventory.data.model.ResponsiveLayout
import com.example.labinventory.data.model.ScreenSize

object ResponsiveDimensions {
    
    @Composable
    fun getScreenPadding(): Dp {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_PHONE -> 12.dp
            ScreenSize.MEDIUM_PHONE -> 16.dp
            ScreenSize.LARGE_PHONE -> 20.dp
            ScreenSize.SMALL_TABLET -> 24.dp
            ScreenSize.LARGE_TABLET -> 32.dp
            ScreenSize.DESKTOP -> 40.dp
        }
    }
    
    @Composable
    fun getGridColumns(): Int {
        val responsiveLayout = LocalResponsiveLayout.current
        return when {
            responsiveLayout.isTablet && responsiveLayout.isLandscape -> 4
            responsiveLayout.isTablet -> 3
            responsiveLayout.isLandscape -> 3
            else -> 2
        }
    }
    
    @Composable
    fun getGridSpacing(): Dp {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_PHONE -> 8.dp
            ScreenSize.MEDIUM_PHONE -> 12.dp
            ScreenSize.LARGE_PHONE -> 16.dp
            ScreenSize.SMALL_TABLET -> 20.dp
            ScreenSize.LARGE_TABLET -> 24.dp
            ScreenSize.DESKTOP -> 32.dp
        }
    }
    
    @Composable
    fun getCardPadding(): Dp {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_PHONE -> 12.dp
            ScreenSize.MEDIUM_PHONE -> 16.dp
            ScreenSize.LARGE_PHONE -> 20.dp
            ScreenSize.SMALL_TABLET -> 24.dp
            ScreenSize.LARGE_TABLET -> 32.dp
            ScreenSize.DESKTOP -> 40.dp
        }
    }
    
    @Composable
    fun getIconSize(): Dp {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_PHONE -> 20.dp
            ScreenSize.MEDIUM_PHONE -> 24.dp
            ScreenSize.LARGE_PHONE -> 28.dp
            ScreenSize.SMALL_TABLET -> 32.dp
            ScreenSize.LARGE_TABLET -> 36.dp
            ScreenSize.DESKTOP -> 40.dp
        }
    }
    
    @Composable
    fun getButtonHeight(): Dp {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_PHONE -> 44.dp
            ScreenSize.MEDIUM_PHONE -> 48.dp
            ScreenSize.LARGE_PHONE -> 52.dp
            ScreenSize.SMALL_TABLET -> 56.dp
            ScreenSize.LARGE_TABLET -> 60.dp
            ScreenSize.DESKTOP -> 64.dp
        }
    }
    
    @Composable
    fun getSearchBarHeight(): Dp {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_PHONE -> 40.dp
            ScreenSize.MEDIUM_PHONE -> 44.dp
            ScreenSize.LARGE_PHONE -> 48.dp
            ScreenSize.SMALL_TABLET -> 52.dp
            ScreenSize.LARGE_TABLET -> 56.dp
            ScreenSize.DESKTOP -> 60.dp
        }
    }
    
    @Composable
    fun getTopBarHeight(): Dp {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_PHONE -> 56.dp
            ScreenSize.MEDIUM_PHONE -> 64.dp
            ScreenSize.LARGE_PHONE -> 72.dp
            ScreenSize.SMALL_TABLET -> 80.dp
            ScreenSize.LARGE_TABLET -> 88.dp
            ScreenSize.DESKTOP -> 96.dp
        }
    }
    
    @Composable
    fun getBottomNavHeight(): Dp {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_PHONE -> 80.dp
            ScreenSize.MEDIUM_PHONE -> 88.dp
            ScreenSize.LARGE_PHONE -> 96.dp
            ScreenSize.SMALL_TABLET -> 104.dp
            ScreenSize.LARGE_TABLET -> 112.dp
            ScreenSize.DESKTOP -> 120.dp
        }
    }
    
    @Composable
    fun getContentMaxWidth(): Dp? {
        val responsiveLayout = LocalResponsiveLayout.current
        return when (responsiveLayout.screenSize) {
            ScreenSize.SMALL_TABLET -> 600.dp
            ScreenSize.LARGE_TABLET -> 800.dp
            ScreenSize.DESKTOP -> 1200.dp
            else -> null // No max width for phones
        }
    }
    
    @Composable
    fun getSidePanelWidth(): Dp? {
        val responsiveLayout = LocalResponsiveLayout.current
        return when {
            responsiveLayout.isTablet && responsiveLayout.isLandscape -> 300.dp
            responsiveLayout.isTablet -> 250.dp
            else -> null
        }
    }
}
