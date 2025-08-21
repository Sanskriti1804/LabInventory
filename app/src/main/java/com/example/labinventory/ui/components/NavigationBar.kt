package com.example.labinventory.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.labinventory.navigation.bottomNavItems
import com.example.labinventory.ui.theme.NavBarDimensions
import com.example.labinventory.ui.theme.highlightColor
import com.example.labinventory.ui.theme.navLabelColor
import com.example.labinventory.ui.theme.someOtherGrayColor
import com.example.labinventory.ui.theme.whiteColor
import com.example.labinventory.util.pxToDp

@Composable
fun CustomNavigationBar(
    bottomBarColor: Color = whiteColor,
    badgeColor: Color = highlightColor,
    selectedColor: Color = highlightColor,
    contentColor: Color = navLabelColor,
    dividerColor: Color = someOtherGrayColor,
    navController: NavHostController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Column(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(NavBarDimensions.DividerHeight)
                .background(dividerColor)
        )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(NavBarDimensions.Height)
            .background(bottomBarColor),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route
            Column(
                modifier = Modifier
                    .clickable{
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    .padding(
                        top = NavBarDimensions.ItemTop,
                        bottom = NavBarDimensions.ItemBottom,
                        start = NavBarDimensions.ItemHorizontal,
                        end = NavBarDimensions.ItemHorizontal
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier) {
                    AppNavIcon(
                        painter = painterResource(id = item.iconResId),
                        iconSize = NavBarDimensions.IconSize,
                        tint = if (isSelected) selectedColor else contentColor
                    )

                    if (item.badgeCount != null || item.hasNews) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(
                                    x = NavBarDimensions.BadgeOffsetX,
                                    y = NavBarDimensions.BadgeOffsetY
                                )
                                .background(badgeColor, shape = CircleShape)
                                .padding(
                                    horizontal = NavBarDimensions.BadgePaddingHorizontal,
                                    vertical = NavBarDimensions.BadgePaddingVertical
                                )
                        ) {
                            if (item.badgeCount != null) {
                                Text(
                                    text = item.badgeCount.toString(),
                                    color = if (isSelected) selectedColor else contentColor,
                                    fontSize = NavBarDimensions.BadgeFontSize
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(NavBarDimensions.LabelSpacing))

                CustomSmallLabel(
                    header = item.label,
                    headerColor =  if (isSelected) selectedColor else contentColor,
                    fontSize =  NavBarDimensions.LabelFontSize,
                    modifier = Modifier.padding(top = NavBarDimensions.LabelTopPadding)
                )
            }
        }

        }
    }
}
