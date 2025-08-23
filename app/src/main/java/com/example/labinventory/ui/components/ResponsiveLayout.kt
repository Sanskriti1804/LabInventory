package com.example.labinventory.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import com.example.labinventory.data.remote.LocalResponsiveLayout
import com.example.labinventory.data.model.ResponsiveLayout
import com.example.labinventory.util.ResponsiveDimensions

@Composable
fun ResponsiveColumn(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    val responsiveLayout = LocalResponsiveLayout.current
    val maxWidth = ResponsiveDimensions.getContentMaxWidth()
    
    Column(
        modifier = modifier
            .then(
                if (maxWidth != null) {
                    Modifier.widthIn(max = maxWidth)
                } else {
                    Modifier
                }
            ),
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

@Composable
fun ResponsiveRow(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
) {
    val responsiveLayout = LocalResponsiveLayout.current
    
    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        content = content
    )
}

@Composable
fun ResponsiveGrid(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val responsiveLayout = LocalResponsiveLayout.current
    val columns = ResponsiveDimensions.getGridColumns()
    val spacing = ResponsiveDimensions.getGridSpacing()
    
    // For tablets and landscape, we can use a more sophisticated grid layout
    if (responsiveLayout.isTablet) {
        ResponsiveTabletGrid(
            modifier = modifier,
            columns = columns,
            spacing = spacing,
            content = content
        )
    } else {
        // For phones, use the existing grid layout
        content()
    }
}

@Composable
private fun ResponsiveTabletGrid(
    modifier: Modifier = Modifier,
    columns: Int,
    spacing: Dp,
    content: @Composable () -> Unit
) {
    val responsiveLayout = LocalResponsiveLayout.current
    
    if (responsiveLayout.isLandscape && responsiveLayout.isTablet) {
        // Landscape tablet: side-by-side layout
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            // Left panel
            Box(
                modifier = Modifier.weight(1f)
            ) {
                content()
            }
            // Right panel could be added here for additional content
        }
    } else {
        // Portrait tablet: grid layout
        content()
    }
}

@Composable
fun ResponsiveCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val responsiveLayout = LocalResponsiveLayout.current
    val padding = ResponsiveDimensions.getCardPadding()
    
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = if (responsiveLayout.isTablet) 2.dp else 1.dp
    ) {
        Box(
            modifier = Modifier.padding(padding)
        ) {
            content()
        }
    }
}

@Composable
fun ResponsiveSpacer(
    modifier: Modifier = Modifier
) {
    val responsiveLayout = LocalResponsiveLayout.current
    val height = when (responsiveLayout.screenSize) {
        com.example.labinventory.data.model.ScreenSize.SMALL_PHONE -> 8.dp
        com.example.labinventory.data.model.ScreenSize.MEDIUM_PHONE -> 12.dp
        com.example.labinventory.data.model.ScreenSize.LARGE_PHONE -> 16.dp
        com.example.labinventory.data.model.ScreenSize.SMALL_TABLET -> 20.dp
        com.example.labinventory.data.model.ScreenSize.LARGE_TABLET -> 24.dp
        com.example.labinventory.data.model.ScreenSize.DESKTOP -> 32.dp
    }
    
    Spacer(modifier = modifier.height(height))
}

@Composable
fun ResponsiveHorizontalSpacer(
    modifier: Modifier = Modifier
) {
    val responsiveLayout = LocalResponsiveLayout.current
    val width = when (responsiveLayout.screenSize) {
        com.example.labinventory.data.model.ScreenSize.SMALL_PHONE -> 8.dp
        com.example.labinventory.data.model.ScreenSize.MEDIUM_PHONE -> 12.dp
        com.example.labinventory.data.model.ScreenSize.LARGE_PHONE -> 16.dp
        com.example.labinventory.data.model.ScreenSize.SMALL_TABLET -> 20.dp
        com.example.labinventory.data.model.ScreenSize.LARGE_TABLET -> 24.dp
        com.example.labinventory.data.model.ScreenSize.DESKTOP -> 32.dp
    }
    
    Spacer(modifier = modifier.width(width))
}
