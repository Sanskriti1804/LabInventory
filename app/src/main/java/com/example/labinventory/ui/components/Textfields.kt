package com.example.labinventory.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.labinventory.R
import com.example.labinventory.ui.theme.TextFieldDimensions
import com.example.labinventory.ui.theme.cardColor
import com.example.labinventory.ui.theme.darkTextColor
import com.example.labinventory.util.pxToDp

@Composable
fun AppTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value : String,
    onValueChange : (String) -> Unit,
    shape: Shape = RoundedCornerShape(TextFieldDimensions.CornerRadius),
    placeholder: String,
    textColor : Color = darkTextColor.copy(alpha = 0.7f),
    containercolor : Color = cardColor,
    minlines : Int = 1,
    maxlines : Int = 1,
    visualTransformation : VisualTransformation = VisualTransformation.None
){
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = shape,
        placeholder = {
            Text(
                text = placeholder,
                modifier = Modifier
                    .padding(TextFieldDimensions.PlaceholderPadding),
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = textColor,
            focusedContainerColor = containercolor,
            unfocusedContainerColor = containercolor,
            disabledContainerColor = containercolor,
            cursorColor = textColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        visualTransformation = visualTransformation,
        minLines = minlines,
        maxLines = maxlines

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredAppTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = RoundedCornerShape(TextFieldDimensions.CornerRadius),
    placeholder: String,
    textColor: Color = darkTextColor.copy(alpha = 0.7f),
    containerColor: Color = cardColor,
    minLines: Int = 1,
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    items: List<String> = emptyList(),
    onItemSelected: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded && items.isNotEmpty(),
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                expanded = true
            },
            shape = shape,
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier.padding(TextFieldDimensions.PlaceholderPadding)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                disabledTextColor = textColor,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                cursorColor = textColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            visualTransformation = visualTransformation,
            minLines = minLines,
            maxLines = maxLines,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor() // dropdown positioning
        )

        ExposedDropdownMenu(
            expanded = expanded && items.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Composable
fun AppDropDownTextField(
    modifier : Modifier,
    value : String,
    items : List<String>,
    onValueChange : (String) -> Unit,
    shape: Shape = RoundedCornerShape(TextFieldDimensions.CornerRadius),
    placeholder: String,
    textColor : Color = darkTextColor.copy(alpha = 0.7f),
    containercolor : Color = cardColor
){
    var expanded by remember { mutableStateOf(false) }

    Box(modifier){
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            shape = shape,
            placeholder = {
                Row {
                    Text(
                        text = placeholder,
                        modifier = Modifier
                            .padding(TextFieldDimensions.Padding),
                    )
                    AppCategoryIcon(
                        painter = painterResource(R.drawable.ic_dropdown),
                        iconDescription = "Drop Down Icon",
                        modifier = Modifier
                            .padding(TextFieldDimensions.Padding)
                    )
                }
            },
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down Icon",
                    tint = textColor,
                    modifier = Modifier
                        .clickable {
                            expanded = !expanded
                        }
                        .padding(TextFieldDimensions.IconPadding)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                focusedContainerColor = containercolor,
                unfocusedContainerColor = containercolor,
                focusedIndicatorColor = textColor
            ),
            modifier = Modifier
                .clickable{
                    expanded = true
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach{
                item ->
                DropdownMenuItem(
                    text = { Text(item)},
                    onClick = {
                        onValueChange(item)
                        expanded = false
                    }
                )
            }
        }
    }
}


