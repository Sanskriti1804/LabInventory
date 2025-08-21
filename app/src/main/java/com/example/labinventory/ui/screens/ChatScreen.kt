package com.example.labinventory.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.labinventory.R
import com.example.labinventory.ui.theme.ChatBottomSheetDimensions
import com.example.labinventory.ui.theme.aiColor
import com.example.labinventory.ui.theme.navBackColor
import com.example.labinventory.ui.theme.someGrayColor
import com.example.labinventory.util.pxToDp
import com.example.labinventory.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatBottomSheet(
    viewModel: SearchViewModel = koinViewModel()
) {
    val query by viewModel.query

    Surface(
        shape = RoundedCornerShape(
            topStart = ChatBottomSheetDimensions.CornerRadius,
            topEnd = ChatBottomSheetDimensions.CornerRadius
        ),
        color = navBackColor,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = ChatBottomSheetDimensions.MinHeight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ChatBottomSheetDimensions.HorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Icons Row
            Box(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(R.drawable.ic_chat),
                    contentDescription = "Chat Icon",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .size(ChatBottomSheetDimensions.ChatIconSize)
                )

                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = "Close",
                        modifier = Modifier.size(ChatBottomSheetDimensions.CloseIconSize),
                        tint = someGrayColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(ChatBottomSheetDimensions.TitleSpacerHeight))

            Text(
                text = "Share your project to get",
                color = aiColor,
                fontFamily = FontFamily(Font(R.font.font_alliance_regular_two)),
                textAlign = TextAlign.Center
            )
            Text(
                text = "equipment suggestions",
                fontSize = 16.sp,
                color = aiColor,
                fontFamily = FontFamily(Font(R.font.font_alliance_regular_two)),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(ChatBottomSheetDimensions.FieldSpacerHeight))

            // Input Field
            SearchInputField(
                query = query,
                onQueryChange = viewModel::onQueryChange,
                onSearch = viewModel::onSearchTriggered
            )
        }
    }
}


@Composable
fun SearchInputField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Enter keyword") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical =  ChatBottomSheetDimensions.InputVerticalPadding),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Go),
        keyboardActions = KeyboardActions(onGo = {
            onSearch()
//            LocalSoftwareKeyboardController.current?.hide()
        }),
        colors = OutlinedTextFieldDefaults.colors()
    )
}

//
//@Preview(showBackground = true)
//@Composable
//fun SearchBottomSheetPreview() {
//    val viewModel = remember { SearchViewModel() }
//    ChatBottomSheet(
//        viewModel = viewModel,
//        onClose = {}
//    )
//}
