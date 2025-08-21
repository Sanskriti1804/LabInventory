package com.example.labinventory.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.example.labinventory.data.model.CompactSpacing
import com.example.labinventory.data.model.ExpandedSpacing
import com.example.labinventory.data.model.LocalSpacing
import com.example.labinventory.data.model.LocalWindowSizeClass
import com.example.labinventory.data.model.MediumSpacing
import com.example.labinventory.data.model.WindowType
import com.example.labinventory.data.model.rememberWindowSizeClass


/* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/


@Composable
fun LabInventoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val windowSizeClass = rememberWindowSizeClass()

    val spacing = when (windowSizeClass.windowType) {
        WindowType.Compact -> CompactSpacing
        WindowType.Medium -> MediumSpacing
        WindowType.Expanded -> ExpandedSpacing
    }


    CompositionLocalProvider(
        LocalWindowSizeClass provides windowSizeClass,
        LocalSpacing provides spacing
    ) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}