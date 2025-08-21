package com.example.labinventory.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.labinventory.R
import com.example.labinventory.data.model.BookingDates
import com.example.labinventory.data.model.BookingTab
import com.example.labinventory.data.model.InChargeInfo
import com.example.labinventory.data.model.ProductInfo
import com.example.labinventory.data.model.Status
import com.example.labinventory.data.model.TabItem
import com.example.labinventory.navigation.Screen
import com.example.labinventory.ui.components.AppNavIcon
import com.example.labinventory.ui.components.CustomLabel
import com.example.labinventory.ui.components.CustomNavigationBar
import com.example.labinventory.ui.components.CustomTopBar
import com.example.labinventory.ui.components.EditButton
import com.example.labinventory.ui.theme.BookingScreenDimensions
import com.example.labinventory.ui.theme.cardColor
import com.example.labinventory.ui.theme.categoryColor
import com.example.labinventory.ui.theme.categoryIconColor
import com.example.labinventory.ui.theme.darkTextColor
import com.example.labinventory.ui.theme.highlightColor
import com.example.labinventory.ui.theme.someGrayColor
import com.example.labinventory.ui.theme.whiteColor
import com.example.labinventory.util.pxToDp
import com.example.labinventory.viewmodel.BookingScreenViewmodel
import org.koin.androidx.compose.koinViewModel


@Composable
fun BookingScreen(
    navController: NavHostController,
    viewModel: BookingScreenViewmodel = koinViewModel()
) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "Bookings")
        },
        bottomBar = {
            CustomNavigationBar(navController = navController)
        },
    ) { paddingValues ->

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(BookingScreenDimensions.ScreenPadding)
            .padding(paddingValues)) {
            Spacer(modifier = Modifier.height(BookingScreenDimensions.TopSpacerHeight))

            BookingTabSelector(
                tabs = listOf(
                    TabItem(BookingTab.Booking_Requests, "Booking Requests", R.drawable.ic_booking_pending, isSelected = true),
                    TabItem(BookingTab.Verified_Bookings, "Approved Bookings", R.drawable.ic_booking_verified, isSelected = false),
                    TabItem(BookingTab.Canceled_Bookings, "Rejected Bookings", R.drawable.ic_booking_canceled, isSelected = false),
                ),
                onTabSelected = { selectedTab -> }
            )

            Spacer(modifier = Modifier.height(BookingScreenDimensions.CardContentSpacing))

            InfoCard(
                productInfo = viewModel.productInfo,
                inChargeInfo = viewModel.inCharge,
                bookingDates = viewModel.bookingDates,
                onEditBooking = { navController.navigate(Screen.CalendarScreen.route)}
            )
        }
    }
}

@Composable
fun BookingTabSelector(tabs: List<TabItem>, onTabSelected: (BookingTab) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BookingScreenDimensions.TabSpacing)
    ) {
        tabs.forEach {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(BookingScreenDimensions.TabPadding)
                    .clickable { onTabSelected(it.tab) }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(BookingScreenDimensions.TabIconSpacing)
                ) {
                    AppNavIcon(
                        painter = painterResource(id = it.iconRes),
                        iconDescription = it.label,
                        iconSize = BookingScreenDimensions.TabIconSize,
                        tint = if (it.isSelected) highlightColor else categoryIconColor
                    )
                    CustomLabel(
                        header = it.label,
                        fontSize = 12.sp,
                        headerColor = if (it.isSelected) highlightColor else categoryIconColor
                    )
                }
            }

        }
    }
}

@Composable
fun InfoCard(
    productInfo: ProductInfo,
    inChargeInfo: InChargeInfo,
    bookingDates: BookingDates,
    onEditBooking: () -> Unit,
    icons: List<Int> = listOf(R.drawable.ic_mail),
    cardShape: Shape = RectangleShape,
    containerColor : Color = cardColor,
) {
    Card(
        shape = cardShape,
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = BookingScreenDimensions.CardHorizontalPadding,
                    vertical = BookingScreenDimensions.CardVerticalPadding
                ),
            verticalArrangement = Arrangement.spacedBy(BookingScreenDimensions.CardContentSpacing)
        ) {

            Row {
                Image(
                    painter = painterResource(R.drawable.temp),
                    contentDescription = "Product Image",

                    modifier = Modifier.size(BookingScreenDimensions.ProductImageSize)
                )
                Spacer(modifier = Modifier.width(BookingScreenDimensions.ProductImageTextSpacing))
                Column(
                    modifier = Modifier.weight(0.8f),
                    verticalArrangement = Arrangement.spacedBy(BookingScreenDimensions.ProductInfoSpacing)
                ) {
                    CustomLabel(
                        header = productInfo.title,
                        fontSize = BookingScreenDimensions.ProductTitleFontSize,
                        modifier = Modifier,
                        headerColor = darkTextColor
                    )
                    CustomLabel(
                        header = productInfo.location,
                        fontSize = BookingScreenDimensions.ProductLocationFontSize,
                        modifier = Modifier,
                        headerColor = someGrayColor
                    )
                    CustomLabel(
                        header = productInfo.status.label,
                        fontSize =  BookingScreenDimensions.ProductStatusFontSize,
                        modifier = Modifier
                            .padding(vertical = BookingScreenDimensions.StatusTopBottomPadding)
                            .background(
                                color = productInfo.status.color,
                                shape = RoundedCornerShape(BookingScreenDimensions.StatusCornerRadius)
                            )
                            .padding(
                                horizontal = BookingScreenDimensions.StatusHorizontalPadding,
                                vertical = BookingScreenDimensions.StatusVerticalPadding
                            ),
                        headerColor = whiteColor
                    )
                }
            }

            Divider(thickness =  BookingScreenDimensions.DividerThickness, color = someGrayColor)


            Column(verticalArrangement = Arrangement.spacedBy(BookingScreenDimensions.InChargeSpacing)) {

                CustomLabel(
                    header = "InCharge",
                    headerColor = darkTextColor.copy(0.9f)
                )
                InChargeRow(label = "Prof.", name = "Sumant Rao")
                InChargeRow(
                    label = "Asst.",
                    name = "Akash Kumar Swami",
                    icons = listOf(R.drawable.ic_mail, R.drawable.ic_call)
                )
            }

            Divider()

            Column(verticalArrangement = Arrangement.spacedBy(BookingScreenDimensions.BookingDatesSpacing)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = BookingScreenDimensions.BookingDatesBottomPadding),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomLabel(
                        header = "Booking Dates",
                        headerColor = darkTextColor.copy(0.9f)
                    )
                    EditButton(
                        onClick = onEditBooking
                    )
                }
                DatesRow(label = "From", name = "2025-08-01")
                DatesRow(label = "To", name = "2025-08-10")
            }
        }
    }
}

@Composable
fun InChargeRoww(label: String, name: String, icons: List<Int>? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CustomLabel(
                header = "$label",
                fontSize = 14.sp,
                headerColor = darkTextColor.copy(0.5f),
                modifier = Modifier.width(BookingScreenDimensions.InChargeLabelWidth)
            )
            CustomLabel(
                header = name,
                fontSize = 14.sp,
                modifier = Modifier,
                headerColor = darkTextColor.copy(0.8f)
            )
        }
        icons?.let {
            Row(horizontalArrangement = Arrangement.spacedBy(BookingScreenDimensions.InChargeRowIconSpacing)) {
                it.forEach { iconRes ->
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Contact Icon"
                    )
                }
            }
        }
    }
}


@Composable
fun DatesRow(label: String, name: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomLabel(
                header = "$label",
                fontSize = 16.sp,
                headerColor = darkTextColor.copy(0.5f),
                modifier = Modifier
                    .width(BookingScreenDimensions.BookingDatesLabelWidth)
            )
            CustomLabel(
                header = name,
                fontSize = BookingScreenDimensions.BookingDatesFontSize,
                headerColor = darkTextColor.copy(0.8f)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBookingScreen() {
//    BookingScreen()
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewBookingTabSelector() {
//    BookingTabSelector(
//        tabs = listOf(
//            TabItem(BookingTab.Booking_Requests,  "Booking Requests",R.drawable.ic_booking_pending, isSelected = true),
//            TabItem(BookingTab.Booking_Requests, "Booking Requests",R.drawable.ic_booking_pending, isSelected = false),
//            TabItem(BookingTab.Booking_Requests, "Booking Requests",R.drawable.ic_booking_pending, isSelected = false)
//        ),
//        onTabSelected = {}
//    )
//}

@Preview(showBackground = true)
@Composable
fun PreviewInfoCard() {
//
}

