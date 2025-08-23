package com.example.labinventory.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.labinventory.data.remote.LocalResponsiveLayout
import com.example.labinventory.ui.components.*
import com.example.labinventory.util.ResponsiveDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsiveDemoScreen(
    navController: NavHostController
) {
    val responsiveLayout = LocalResponsiveLayout.current
    
    ResponsiveAppLayout(
        topBar = {
            TopAppBar(
                title = { Text("Responsive Demo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            CustomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        ResponsiveColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header section
            ResponsiveCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Responsive Design Demo",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    ResponsiveSpacer()
                    
                    Text(
                        text = "Current Screen: ${responsiveLayout.screenSize}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Text(
                        text = "Orientation: ${if (responsiveLayout.isPortrait) "Portrait" else "Landscape"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Text(
                        text = "Device: ${if (responsiveLayout.isTablet) "Tablet" else "Phone"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            ResponsiveSpacer()
            
            // Responsive grid demo
            Text(
                text = "Responsive Grid (${ResponsiveDimensions.getGridColumns()} columns)",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
            
            ResponsiveSpacer()
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(ResponsiveDimensions.getGridColumns()),
                horizontalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getGridSpacing()),
                verticalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getGridSpacing())
            ) {
                items(10) { index ->
                    ResponsiveCard(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Item ${index + 1}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium
                            )
                            
                            ResponsiveSpacer()
                            
                            Text(
                                text = "Grid adapts to screen size",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
            
            ResponsiveSpacer()
            
            // Responsive buttons demo
            Text(
                text = "Responsive Buttons",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
            
            ResponsiveSpacer()
            
            ResponsiveRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(ResponsiveDimensions.getButtonHeight())
                ) {
                    Text("Primary Button")
                }
                
                ResponsiveHorizontalSpacer()
                
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(ResponsiveDimensions.getButtonHeight())
                ) {
                    Text("Secondary Button")
                }
            }
            
            ResponsiveSpacer()
            
            // Responsive spacing demo
            Text(
                text = "Responsive Spacing",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
            
            ResponsiveSpacer()
            
            ResponsiveCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Screen Padding: ${ResponsiveDimensions.getScreenPadding()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    ResponsiveSpacer()
                    
                    Text(
                        text = "Card Padding: ${ResponsiveDimensions.getCardPadding()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    ResponsiveSpacer()
                    
                    Text(
                        text = "Grid Spacing: ${ResponsiveDimensions.getGridSpacing()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    ResponsiveSpacer()
                    
                    Text(
                        text = "Icon Size: ${ResponsiveDimensions.getIconSize()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            ResponsiveSpacer()
            
            // Responsive content wrapper demo
            if (responsiveLayout.isTablet) {
                ResponsiveContentWrapper {
                    ResponsiveCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Content Wrapper Demo",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                            
                            ResponsiveSpacer()
                            
                            Text(
                                text = "This content is centered on tablets and has a max width constraint",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
