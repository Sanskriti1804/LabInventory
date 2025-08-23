package com.example.labinventory.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.labinventory.data.remote.LocalResponsiveLayout
import com.example.labinventory.data.model.ResponsiveLayout
import com.example.labinventory.navigation.bottomNavItems
import com.example.labinventory.ui.theme.navLabelColor
import com.example.labinventory.ui.theme.whiteColor
import com.example.labinventory.util.ResponsiveDimensions

@Composable
fun ResponsiveNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val responsiveLayout = LocalResponsiveLayout.current
    
    if (responsiveLayout.isTablet && responsiveLayout.isLandscape) {
        // Tablet landscape: Side navigation
        ResponsiveSideNavigation(navController, modifier)
    } else {
        // Phone or tablet portrait: Bottom navigation
        ResponsiveBottomNavigation(navController, modifier)
    }
}

@Composable
private fun ResponsiveSideNavigation(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    
    Column(
        modifier = modifier
            .width(ResponsiveDimensions.getSidePanelWidth() ?: 250.dp)
            .fillMaxHeight()
            .background(whiteColor)
            .padding(ResponsiveDimensions.getScreenPadding()),
        verticalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getScreenPadding())
    ) {
        Text(
            text = "Lab Inventory",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = navLabelColor,
            modifier = Modifier.padding(bottom = ResponsiveDimensions.getScreenPadding() * 2)
        )
        
        bottomNavItems.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = ResponsiveDimensions.getScreenPadding() / 2),
                shape = RoundedCornerShape(ResponsiveDimensions.getScreenPadding()),
                color = if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        .padding(ResponsiveDimensions.getScreenPadding()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getScreenPadding())
                ) {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = item.navIconDesc,
                        tint = if (selected) MaterialTheme.colorScheme.primary else navLabelColor,
                        modifier = Modifier.size(ResponsiveDimensions.getIconSize())
                    )
                    
                    Text(
                        text = item.label,
                        color = if (selected) MaterialTheme.colorScheme.primary else navLabelColor,
                        fontSize = 16.sp,
                        fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
private fun ResponsiveBottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    
    NavigationBar(
        modifier = modifier
            .height(ResponsiveDimensions.getBottomNavHeight()),
        containerColor = whiteColor,
        tonalElevation = 8.dp
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = item.navIconDesc,
                        modifier = Modifier.size(ResponsiveDimensions.getIconSize())
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 14.sp
                    )
                },
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = navLabelColor,
                    unselectedTextColor = navLabelColor,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
