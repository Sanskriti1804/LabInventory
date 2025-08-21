package com.example.labinventory.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.labinventory.ui.theme.TopBarDimensions
import com.example.labinventory.ui.theme.headerColor
import com.example.labinventory.ui.theme.whiteColor
import com.example.labinventory.util.pxToDp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    onNavigationClick: (() -> Unit)? = null,
    containerColor: Color = whiteColor,
    titleColor: Color = headerColor,
){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = TopBarDimensions.TopPadding)
                .background(containerColor),
            verticalAlignment = Alignment.Top
        ) {
            if (onNavigationClick != null) {
                AppNavBackIcon(
                    onClick = onNavigationClick,
                    modifier = Modifier
                        .padding(start = TopBarDimensions.NavIconStartPadding,
                            end = TopBarDimensions.NavIconEndPadding,
                            top = TopBarDimensions.NavIconTopPadding
                        )
                        .align(Alignment.CenterVertically)

                )
            }
            else{
                Spacer(modifier = Modifier.width(TopBarDimensions.HorizontalPadding))

            }

            CustomLabel(
                header = title,
                headerColor = titleColor,
                fontSize = 25.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = TopBarDimensions.TitleTopPadding)
            )
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CustomTopBarPreview(){
    CustomTopBar(
        title = "Preview Title",
        onNavigationClick = {},
    )
}


