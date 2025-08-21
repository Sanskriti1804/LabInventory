package com.example.labinventory.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labinventory.R
import com.example.labinventory.ui.components.AppButton
import com.example.labinventory.ui.components.AppDropDownTextField
import com.example.labinventory.ui.components.AppTextField
import com.example.labinventory.ui.components.CustomLabel
import com.example.labinventory.ui.components.CustomTopBar
import com.example.labinventory.ui.theme.NewEquipmentDimensions
import com.example.labinventory.ui.theme.cardColor
import com.example.labinventory.ui.theme.darkTextColor
import com.example.labinventory.ui.theme.whiteColor
import com.example.labinventory.util.pxToDp

@SuppressLint("RememberReturnType")
@Composable
fun NewEquipmentScreen() {
    var value by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopBar(title = "Add Equipment")
        },
        containerColor = whiteColor,
        bottomBar = {
            AppButton(
                buttonText = "ADD EQUIPMENT",
                onClick = {},
                modifier = Modifier.padding(NewEquipmentDimensions.BottomButtonPadding)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(NewEquipmentDimensions.ScreenPadding)
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(NewEquipmentDimensions.FieldSpacing)
        ) {
            AddImageCard()

            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Equipment Name"
            )
            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Brand"
            )
            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Model"
            )
            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Location"
            )
            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Incharge (prof)"
            )
            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Incharge (Assisstant)"
            )
            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Additional Information",
                maxlines = 3
            )
            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Link (How to use video)"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(NewEquipmentDimensions.DropDownRowSpacing)
            ) {
                AppDropDownTextField(
                    modifier = Modifier.weight(1f),
                    value = value,
                    onValueChange = { value = it },
                    placeholder = "General Category",
                    items = listOf("Yes", "No")
                )
                AppDropDownTextField(
                    modifier = Modifier.weight(1f),
                    value = value,
                    onValueChange = { value = it },
                    placeholder = "Sub Category",
                    items = listOf("Yes", "No")
                )
            }
        }
    }
}

@Composable
fun AddImageCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(NewEquipmentDimensions.ImageCardHeight),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        onClick = onClick,
        shape = RoundedCornerShape(NewEquipmentDimensions.ImageCardCornerRadius)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add_image),
                contentDescription = "Add Image",
                modifier = Modifier.size(NewEquipmentDimensions.ImageIconSize)
            )

            Spacer(modifier = Modifier.height(NewEquipmentDimensions.ImageCardSpacerHeight))
            CustomLabel(
                header = "Add Image",
                fontSize = NewEquipmentDimensions.ImageCardLabelSize,
                headerColor = darkTextColor.copy(0.7f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddEquipmentScreenPreview() {
    NewEquipmentScreen()
}
