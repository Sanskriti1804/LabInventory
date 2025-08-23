package com.example.labinventory.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.labinventory.data.remote.LocalResponsiveLayout
import com.example.labinventory.data.model.ScreenSize

@Composable
fun getResponsiveTypography(): Typography {
    val responsiveLayout = LocalResponsiveLayout.current

    return Typography(
        displayLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 22.sp
                ScreenSize.MEDIUM_PHONE -> 26.sp
                ScreenSize.LARGE_PHONE -> 30.sp
                ScreenSize.SMALL_TABLET -> 34.sp
                ScreenSize.LARGE_TABLET -> 38.sp
                ScreenSize.DESKTOP -> 42.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 38.sp
                ScreenSize.MEDIUM_PHONE -> 42.sp
                ScreenSize.LARGE_PHONE -> 46.sp
                ScreenSize.SMALL_TABLET -> 50.sp
                ScreenSize.LARGE_TABLET -> 54.sp
                ScreenSize.DESKTOP -> 58.sp
            }
        ),
        displayMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 18.sp
                ScreenSize.MEDIUM_PHONE -> 22.sp
                ScreenSize.LARGE_PHONE -> 26.sp
                ScreenSize.SMALL_TABLET -> 30.sp
                ScreenSize.LARGE_TABLET -> 34.sp
                ScreenSize.DESKTOP -> 38.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 34.sp
                ScreenSize.MEDIUM_PHONE -> 38.sp
                ScreenSize.LARGE_PHONE -> 42.sp
                ScreenSize.SMALL_TABLET -> 46.sp
                ScreenSize.LARGE_TABLET -> 50.sp
                ScreenSize.DESKTOP -> 54.sp
            }
        ),
        displaySmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 14.sp
                ScreenSize.MEDIUM_PHONE -> 18.sp
                ScreenSize.LARGE_PHONE -> 22.sp
                ScreenSize.SMALL_TABLET -> 26.sp
                ScreenSize.LARGE_TABLET -> 30.sp
                ScreenSize.DESKTOP -> 34.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 30.sp
                ScreenSize.MEDIUM_PHONE -> 34.sp
                ScreenSize.LARGE_PHONE -> 38.sp
                ScreenSize.SMALL_TABLET -> 42.sp
                ScreenSize.LARGE_TABLET -> 46.sp
                ScreenSize.DESKTOP -> 50.sp
            }
        ),
        headlineLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 10.sp
                ScreenSize.MEDIUM_PHONE -> 12.sp
                ScreenSize.LARGE_PHONE -> 14.sp
                ScreenSize.SMALL_TABLET -> 16.sp
                ScreenSize.LARGE_TABLET -> 18.sp
                ScreenSize.DESKTOP -> 20.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 26.sp
                ScreenSize.MEDIUM_PHONE -> 28.sp
                ScreenSize.LARGE_PHONE -> 30.sp
                ScreenSize.SMALL_TABLET -> 32.sp
                ScreenSize.LARGE_TABLET -> 34.sp
                ScreenSize.DESKTOP -> 36.sp
            }
        ),
        headlineMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 8.sp
                ScreenSize.MEDIUM_PHONE -> 10.sp
                ScreenSize.LARGE_PHONE -> 12.sp
                ScreenSize.SMALL_TABLET -> 14.sp
                ScreenSize.LARGE_TABLET -> 16.sp
                ScreenSize.DESKTOP -> 18.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 24.sp
                ScreenSize.MEDIUM_PHONE -> 26.sp
                ScreenSize.LARGE_PHONE -> 28.sp
                ScreenSize.SMALL_TABLET -> 30.sp
                ScreenSize.LARGE_TABLET -> 32.sp
                ScreenSize.DESKTOP -> 34.sp
            }
        ),
        headlineSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 6.sp
                ScreenSize.MEDIUM_PHONE -> 8.sp
                ScreenSize.LARGE_PHONE -> 10.sp
                ScreenSize.SMALL_TABLET -> 12.sp
                ScreenSize.LARGE_TABLET -> 14.sp
                ScreenSize.DESKTOP -> 16.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 22.sp
                ScreenSize.MEDIUM_PHONE -> 24.sp
                ScreenSize.LARGE_PHONE -> 26.sp
                ScreenSize.SMALL_TABLET -> 28.sp
                ScreenSize.LARGE_TABLET -> 30.sp
                ScreenSize.DESKTOP -> 32.sp
            }
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 4.sp
                ScreenSize.MEDIUM_PHONE -> 6.sp
                ScreenSize.LARGE_PHONE -> 8.sp
                ScreenSize.SMALL_TABLET -> 10.sp
                ScreenSize.LARGE_TABLET -> 12.sp
                ScreenSize.DESKTOP -> 14.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 18.sp
                ScreenSize.MEDIUM_PHONE -> 20.sp
                ScreenSize.LARGE_PHONE -> 22.sp
                ScreenSize.SMALL_TABLET -> 24.sp
                ScreenSize.LARGE_TABLET -> 26.sp
                ScreenSize.DESKTOP -> 28.sp
            }
        ),
        titleMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 2.sp
                ScreenSize.MEDIUM_PHONE -> 4.sp
                ScreenSize.LARGE_PHONE -> 6.sp
                ScreenSize.SMALL_TABLET -> 8.sp
                ScreenSize.LARGE_TABLET -> 10.sp
                ScreenSize.DESKTOP -> 12.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 14.sp
                ScreenSize.MEDIUM_PHONE -> 16.sp
                ScreenSize.LARGE_PHONE -> 18.sp
                ScreenSize.SMALL_TABLET -> 20.sp
                ScreenSize.LARGE_TABLET -> 22.sp
                ScreenSize.DESKTOP -> 24.sp
            }
        ),
        titleSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 0.sp
                ScreenSize.MEDIUM_PHONE -> 2.sp
                ScreenSize.LARGE_PHONE -> 4.sp
                ScreenSize.SMALL_TABLET -> 6.sp
                ScreenSize.LARGE_TABLET -> 8.sp
                ScreenSize.DESKTOP -> 10.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 12.sp
                ScreenSize.MEDIUM_PHONE -> 14.sp
                ScreenSize.LARGE_PHONE -> 16.sp
                ScreenSize.SMALL_TABLET -> 18.sp
                ScreenSize.LARGE_TABLET -> 20.sp
                ScreenSize.DESKTOP -> 22.sp
            }
        ),
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 2.sp
                ScreenSize.MEDIUM_PHONE -> 4.sp
                ScreenSize.LARGE_PHONE -> 6.sp
                ScreenSize.SMALL_TABLET -> 8.sp
                ScreenSize.LARGE_TABLET -> 10.sp
                ScreenSize.DESKTOP -> 12.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 16.sp
                ScreenSize.MEDIUM_PHONE -> 18.sp
                ScreenSize.LARGE_PHONE -> 20.sp
                ScreenSize.SMALL_TABLET -> 22.sp
                ScreenSize.LARGE_TABLET -> 24.sp
                ScreenSize.DESKTOP -> 26.sp
            }
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 0.sp
                ScreenSize.MEDIUM_PHONE -> 2.sp
                ScreenSize.LARGE_PHONE -> 4.sp
                ScreenSize.SMALL_TABLET -> 6.sp
                ScreenSize.LARGE_TABLET -> 8.sp
                ScreenSize.DESKTOP -> 10.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 14.sp
                ScreenSize.MEDIUM_PHONE -> 16.sp
                ScreenSize.LARGE_PHONE -> 18.sp
                ScreenSize.SMALL_TABLET -> 20.sp
                ScreenSize.LARGE_TABLET -> 22.sp
                ScreenSize.DESKTOP -> 24.sp
            }
        ),
        bodySmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 0.sp,
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 10.sp
                ScreenSize.MEDIUM_PHONE -> 12.sp
                ScreenSize.LARGE_PHONE -> 14.sp
                ScreenSize.SMALL_TABLET -> 16.sp
                ScreenSize.LARGE_TABLET -> 18.sp
                ScreenSize.DESKTOP -> 20.sp
            }
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 0.sp
                ScreenSize.MEDIUM_PHONE -> 2.sp
                ScreenSize.LARGE_PHONE -> 4.sp
                ScreenSize.SMALL_TABLET -> 6.sp
                ScreenSize.LARGE_TABLET -> 8.sp
                ScreenSize.DESKTOP -> 10.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 14.sp
                ScreenSize.MEDIUM_PHONE -> 16.sp
                ScreenSize.LARGE_PHONE -> 18.sp
                ScreenSize.SMALL_TABLET -> 20.sp
                ScreenSize.LARGE_TABLET -> 22.sp
                ScreenSize.DESKTOP -> 24.sp
            }
        ),
        labelMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 0.sp
                ScreenSize.MEDIUM_PHONE -> 2.sp
                ScreenSize.LARGE_PHONE -> 4.sp
                ScreenSize.SMALL_TABLET -> 6.sp
                ScreenSize.LARGE_TABLET -> 8.sp
                ScreenSize.DESKTOP -> 10.sp
            },
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 10.sp
                ScreenSize.MEDIUM_PHONE -> 12.sp
                ScreenSize.LARGE_PHONE -> 14.sp
                ScreenSize.SMALL_TABLET -> 16.sp
                ScreenSize.LARGE_TABLET -> 18.sp
                ScreenSize.DESKTOP -> 20.sp
            }
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 0.sp,
            lineHeight = when (responsiveLayout.screenSize) {
                ScreenSize.SMALL_PHONE -> 8.sp
                ScreenSize.MEDIUM_PHONE -> 10.sp
                ScreenSize.LARGE_PHONE -> 12.sp
                ScreenSize.SMALL_TABLET -> 14.sp
                ScreenSize.LARGE_TABLET -> 16.sp
                ScreenSize.DESKTOP -> 18.sp
            }
        )
    )
}
