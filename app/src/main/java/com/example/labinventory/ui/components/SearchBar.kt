package com.example.labinventory.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labinventory.R
import com.example.labinventory.ui.theme.SearchBarDimensions
import com.example.labinventory.ui.theme.Typography
import com.example.labinventory.ui.theme.labelColor
import com.example.labinventory.ui.theme.searchBarColor
import com.example.labinventory.util.pxToDp


@Composable
fun AppSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String = "Search",
    searchIcon: Painter = painterResource(id = R.drawable.search),
    iconDescription: String = "Search",
    backgroundColor: Color = searchBarColor,
    textColor: Color = labelColor,
    shape: Shape = RoundedCornerShape(SearchBarDimensions.CornerRadius),
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .height(SearchBarDimensions.Height)
            .fillMaxWidth()
            .padding(horizontal = SearchBarDimensions.HorizontalPadding),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = searchIcon,
                contentDescription = iconDescription,
                modifier = Modifier
                    .size(SearchBarDimensions.IconSize),
                tint = textColor
            )
            Spacer(modifier = Modifier.width(SearchBarDimensions.IconTextSpacing))

            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = Typography.labelSmall.copy(
                    color = labelColor,
                    fontSize = SearchBarDimensions.FontSize
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = pxToDp(8)), // vertical centering
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = Typography.labelSmall.copy(
                                color = labelColor,
                                fontSize = SearchBarDimensions.FontSize
                            )
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}


