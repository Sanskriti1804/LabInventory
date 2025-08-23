# LabInventory Responsive Design Guide

This guide explains how to use the responsive design system implemented in the LabInventory app to create adaptive layouts for different screen sizes, orientations, and devices.

## Overview

The responsive system automatically adapts your app's layout based on:
- **Screen Size**: Small phone, medium phone, large phone, small tablet, large tablet, desktop
- **Orientation**: Portrait and landscape modes
- **Device Type**: Phone vs tablet detection

## Core Components

### 1. ResponsiveAppWrapper
The main wrapper that provides responsive context throughout your app.

```kotlin
@Composable
fun MainApp() {
    ResponsiveAppWrapper {
        // Your app content here
        NavGraph()
    }
}
```

### 2. ResponsiveLayout Data Class
Contains information about the current screen configuration:

```kotlin
data class ResponsiveLayout(
    val screenWidth: Dp,
    val screenHeight: Dp,
    val topPadding: Dp,
    val isTablet: Boolean,
    val isPortrait: Boolean,
    val screenSize: ScreenSize,
    val isLandscape: Boolean
)
```

### 3. Screen Size Categories
- `SMALL_PHONE`: < 360dp
- `MEDIUM_PHONE`: 360dp - 480dp
- `LARGE_PHONE`: 480dp - 600dp
- `SMALL_TABLET`: 600dp - 840dp
- `LARGE_TABLET`: 840dp - 1200dp
- `DESKTOP`: > 1200dp

## Responsive Components

### ResponsiveColumn
Automatically centers content and applies max-width constraints for tablets and desktop:

```kotlin
ResponsiveColumn {
    // Your content here
    Text("This will be centered on tablets")
}
```

### ResponsiveRow
Provides responsive row layouts:

```kotlin
ResponsiveRow {
    // Your row content
}
```

### ResponsiveCard
Adaptive card with responsive padding and elevation:

```kotlin
ResponsiveCard {
    // Card content
}
```

### ResponsiveSpacer
Adaptive spacing that adjusts to screen size:

```kotlin
ResponsiveSpacer() // Vertical spacing
ResponsiveHorizontalSpacer() // Horizontal spacing
```

## Responsive Dimensions

Use `ResponsiveDimensions` to get adaptive measurements:

```kotlin
// Screen padding adapts to device size
val padding = ResponsiveDimensions.getScreenPadding()

// Grid columns adapt to screen size and orientation
val columns = ResponsiveDimensions.getGridColumns()

// Button height scales with device
val buttonHeight = ResponsiveDimensions.getButtonHeight()

// Icon sizes adapt to screen
val iconSize = ResponsiveDimensions.getIconSize()
```

## Responsive Navigation

The navigation automatically adapts:
- **Phones & Portrait Tablets**: Bottom navigation bar
- **Landscape Tablets**: Side navigation panel

```kotlin
CustomNavigationBar(navController = navController)
// Automatically uses ResponsiveNavigationBar internally
```

## Responsive Layouts

### ResponsiveAppLayout
Provides adaptive app structure:

```kotlin
ResponsiveAppLayout(
    topBar = { TopAppBar() },
    bottomBar = { BottomNavigation() },
    floatingActionButton = { FAB() }
) { paddingValues ->
    // Main content with adaptive padding
}
```

### ResponsiveContentWrapper
Centers content on larger screens:

```kotlin
ResponsiveContentWrapper {
    // Content will be centered on tablets/desktop
}
```

### ResponsiveGridWrapper
Adaptive grid layouts:

```kotlin
ResponsiveGridWrapper {
    // Grid content that adapts to screen size
}
```

## Responsive Typography

Use the responsive typography system:

```kotlin
val typography = getResponsiveTypography()
// Font sizes automatically scale with screen size
```

## Best Practices

### 1. Always Use Responsive Components
Replace standard components with responsive equivalents:
- `Column` → `ResponsiveColumn`
- `Row` → `ResponsiveRow`
- `Card` → `ResponsiveCard`
- `Spacer` → `ResponsiveSpacer`

### 2. Use Responsive Dimensions
Avoid hardcoded values:
```kotlin
// ❌ Don't do this
.padding(16.dp)

// ✅ Do this instead
.padding(ResponsiveDimensions.getScreenPadding())
```

### 3. Test Different Screen Sizes
Use Android Studio's layout preview to test:
- Small phone (320dp)
- Large phone (480dp)
- Small tablet (600dp)
- Large tablet (840dp)

### 4. Handle Orientation Changes
Test both portrait and landscape modes, especially on tablets.

### 5. Use Adaptive Grids
```kotlin
LazyVerticalGrid(
    columns = GridCells.Fixed(ResponsiveDimensions.getGridColumns()),
    horizontalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getGridSpacing())
) {
    // Grid items
}
```

## Example Implementation

Here's how to make a screen responsive:

```kotlin
@Composable
fun ResponsiveScreen() {
    ResponsiveColumn {
        // Header
        Text(
            text = "Screen Title",
            style = MaterialTheme.typography.headlineLarge
        )
        
        ResponsiveSpacer()
        
        // Content grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(ResponsiveDimensions.getGridColumns()),
            horizontalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getGridSpacing())
        ) {
            items(items) { item ->
                ResponsiveCard {
                    // Card content
                }
            }
        }
    }
}
```

## Testing Responsiveness

1. **Emulator Testing**: Use different device configurations
2. **Layout Inspector**: Check how components adapt
3. **Rotation Testing**: Test portrait/landscape transitions
4. **Multi-window**: Test split-screen scenarios

## Troubleshooting

### Common Issues

1. **Content not centering on tablets**: Use `ResponsiveContentWrapper`
2. **Grid not adapting**: Use `ResponsiveDimensions.getGridColumns()`
3. **Spacing inconsistent**: Use `ResponsiveSpacer` instead of fixed spacers
4. **Navigation not adapting**: Ensure `ResponsiveAppWrapper` is used

### Debug Information

Access responsive layout information anywhere in your composables:

```kotlin
val responsiveLayout = LocalResponsiveLayout.current
Log.d("Responsive", "Screen size: ${responsiveLayout.screenSize}")
Log.d("Responsive", "Is tablet: ${responsiveLayout.isTablet}")
Log.d("Responsive", "Is landscape: ${responsiveLayout.isLandscape}")
```

## Migration Guide

To migrate existing screens to be responsive:

1. Wrap with `ResponsiveAppWrapper` in MainActivity
2. Replace `Column` with `ResponsiveColumn`
3. Replace `Row` with `ResponsiveRow`
4. Replace hardcoded dimensions with `ResponsiveDimensions`
5. Replace `Spacer` with `ResponsiveSpacer`
6. Update grid columns to use `ResponsiveDimensions.getGridColumns()`
7. Test on different screen sizes and orientations

This responsive system ensures your app provides an optimal user experience across all devices and orientations while maintaining the MVVM architecture and following your existing code structure.
