package com.example.labinventory.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labinventory.data.remote.LocalResponsiveLayout
import com.example.labinventory.data.model.ResponsiveLayout
import com.example.labinventory.util.ResponsiveDimensions

@Composable
fun ResponsiveAppLayout(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    val responsiveLayout = LocalResponsiveLayout.current
    
    if (responsiveLayout.isTablet && responsiveLayout.isLandscape) {
        // Tablet landscape: Side-by-side layout
        ResponsiveTabletLayout(
            modifier = modifier,
            topBar = topBar,
            bottomBar = bottomBar,
            floatingActionButton = floatingActionButton,
            content = content
        )
    } else {
        // Phone or tablet portrait: Standard layout
        ResponsiveStandardLayout(
            modifier = modifier,
            topBar = topBar,
            bottomBar = bottomBar,
            floatingActionButton = floatingActionButton,
            content = content
        )
    }
}

@Composable
private fun ResponsiveTabletLayout(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        // Left panel for navigation (if bottomBar is provided)
        if (bottomBar != null) {
            Box(
                modifier = Modifier
                    .width(ResponsiveDimensions.getSidePanelWidth() ?: 250.dp)
                    .fillMaxHeight()
            ) {
                bottomBar()
            }
        }
        
        // Main content area
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Top bar
            topBar?.invoke()
            
            // Main content
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                content(PaddingValues(ResponsiveDimensions.getScreenPadding()))
                
                // Floating action button
                floatingActionButton?.invoke()
            }
        }
    }
}

@Composable
private fun ResponsiveStandardLayout(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Top bar
        topBar?.invoke()
        
        // Main content
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            content(PaddingValues(ResponsiveDimensions.getScreenPadding()))
            
            // Floating action button
            floatingActionButton?.invoke()
        }
        
        // Bottom bar
        bottomBar?.invoke()
    }
}

@Composable
fun ResponsiveContentWrapper(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val responsiveLayout = LocalResponsiveLayout.current
    val maxWidth = ResponsiveDimensions.getContentMaxWidth()
    
    Box(
        modifier = modifier
            .then(
                if (maxWidth != null) {
                    Modifier.widthIn(max = maxWidth)
                } else {
                    Modifier
                }
            )
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        content()
    }
}

@Composable
fun ResponsiveGridWrapper(
    modifier: Modifier = Modifier,
    columns: Int = ResponsiveDimensions.getGridColumns(),
    content: @Composable () -> Unit
) {
    val responsiveLayout = LocalResponsiveLayout.current
    
    if (responsiveLayout.isTablet && responsiveLayout.isLandscape) {
        // Landscape tablet: Use more columns
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getGridSpacing())
        ) {
            repeat(columns) { index ->
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    // This would need to be adapted based on your grid content
                    content()
                }
            }
        }
    } else {
        // Standard grid layout
        content()
    }
}
