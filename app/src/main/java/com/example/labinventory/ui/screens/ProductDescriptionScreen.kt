package com.example.labinventory.ui.screens

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.labinventory.R
import com.example.labinventory.data.model.UiState
import com.example.labinventory.data.model.UserRole
import com.example.labinventory.navigation.Screen
import com.example.labinventory.ui.components.AppButton
import com.example.labinventory.ui.components.AppCategoryIcon
import com.example.labinventory.ui.components.AppCircularIcon
import com.example.labinventory.ui.components.AppFAB
import com.example.labinventory.ui.components.CustomLabel
import com.example.labinventory.ui.components.CustomTopBar
import com.example.labinventory.ui.theme.cardColor
import com.example.labinventory.ui.theme.circularBoxColor
import com.example.labinventory.ui.theme.darkTextColor
import com.example.labinventory.ui.theme.editCardTextColor
import com.example.labinventory.ui.theme.headerColor
import com.example.labinventory.ui.theme.highlightColor
import com.example.labinventory.ui.theme.weekendColor
import com.example.labinventory.ui.theme.whiteColor
import com.example.labinventory.util.ResponsiveLayout
import com.example.labinventory.util.pxToDp
import com.example.labinventory.viewmodel.FacilitiesViewModel
import com.example.labinventory.viewmodel.UserSessionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProdDescScreen(
    sessionViewModel: UserSessionViewModel = koinViewModel(),
    navController: NavHostController
) {
    val userRole = sessionViewModel.userRole

    val productImage = listOf(
        R.drawable.temp,
        R.drawable.temp,
        R.drawable.temp
    )

    val pagerState = rememberPagerState(pageCount = { productImage.size })
    val pagerInteractionSource = remember { MutableInteractionSource() }
    val pagerIsPressed by pagerInteractionSource.collectIsPressedAsState()
    val pagerIsDragged by pagerState.interactionSource.collectIsDraggedAsState()
    val autoAdvance = !pagerIsDragged && !pagerIsPressed

    LaunchedEffect(autoAdvance) {
        if (autoAdvance) {
            while (true) {
                delay(2000)
                val nextPage = (pagerState.currentPage + 1) % productImage.size
                pagerState.animateScrollToPage(page = nextPage)
            }
        }
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Camera",
                onNavigationClick = {
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            if (userRole == UserRole.USER){
                AppButton(
                    onClick = {
                        navController.navigate(Screen.CalendarScreen.route)
                    },
                    buttonText = "BOOK NOW",
                    modifier = Modifier.padding(ResponsiveLayout.getHorizontalPadding())
                )
            }
            else{
                ActionCard(
                    onEditClick = {},
                    onDeleteClick = {},
                    onBookClick = {},
                    modifier = Modifier.padding(ResponsiveLayout.getHorizontalPadding())
                )
            }
        },
        containerColor = whiteColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(ResponsiveLayout.getHorizontalPadding()),
            verticalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(12.dp, 16.dp, 20.dp))
        ) {
            Spacer(modifier = Modifier.height(ResponsiveLayout.getResponsivePadding(20.dp, 24.dp, 28.dp)))
            ProductCarousel(
                images = productImage,
                pageInteractionSource = pagerInteractionSource,
                pagerState = pagerState
            )

            ProductDescriptionCard(modifier = Modifier)

            InChargeCard()
            AdditionalInfoCard()
            UseCard()
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductCarousel(
    images : List<Int>,
    imageDescription : String = "Equipment images",
    contentScale: ContentScale = ContentScale.Crop,
    pagerState: PagerState,
    pageInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    backgroundColor : Color = cardColor,
    inactiveColor : Color = Color.DarkGray,
    activeColor : Color = highlightColor,
    indicatorShape: Shape = CircleShape,
    indicatorSize : Dp = ResponsiveLayout.getResponsiveSize(6.dp, 8.dp, 10.dp),
    isFav : Boolean = false
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(ResponsiveLayout.getResponsiveSize(200.dp, 240.dp, 280.dp)),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(ResponsiveLayout.getResponsivePadding(16.dp, 20.dp, 24.dp))
        ) {
            HorizontalPager(
                state = pagerState, // Use the passed pagerState
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable(
                        // interactionSource allows observing and controlling the visual state of the pager (e.g., pressed, hovered).
                        interactionSource = pageInteractionSource,
                        // indication provides visual feedback for interactions (e.g., ripples on click). LocalIndication.current uses the default indication provided by the theme.
                        indication = LocalIndication.current
                    ){}
            ) {
                    page ->
                AsyncImage(
                    model = images[page],
                    contentDescription = imageDescription,
                    contentScale = contentScale,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
                         Spacer(modifier = Modifier.height(ResponsiveLayout.getResponsivePadding(16.dp, 18.dp, 20.dp)))
             HorizontalPagerIndicator(
                 pagerState = pagerState, // Use the passed pagerState
                 pageCount = images.size,
                 inactiveColor = inactiveColor,
                 activeColor = activeColor,
                 indicatorShape = indicatorShape,
                 modifier = Modifier
                     .size(indicatorSize)
             )
             Spacer(modifier = Modifier.height(ResponsiveLayout.getResponsivePadding(3.dp, 4.dp, 5.dp)))
        }

    }
}

@Composable
fun ProductDescriptionCard(
    modifier: Modifier,
    shape: Shape = RectangleShape,
    cardContainerColor: Color = cardColor
) {
    val facilitiesViewModel : FacilitiesViewModel = koinViewModel ()
    val facilitiesList = facilitiesViewModel.facilitiesState

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(ResponsiveLayout.getResponsiveSize(190.dp, 210.dp, 230.dp)),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = cardContainerColor
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ResponsiveLayout.getResponsivePadding(16.dp, 18.dp, 20.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(16.dp, 18.dp, 20.dp))
            ) {
                CustomLabel(
                    header = "Canon EOS R50 V",
                    headerColor = darkTextColor,
                    fontSize = ResponsiveLayout.getResponsiveFontSize(16.sp, 18.sp, 20.sp),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(ResponsiveLayout.getResponsivePadding(3.dp, 4.dp, 5.dp)))

                when(facilitiesList){
                    is UiState.Loading -> {
                        Text("Loading facilities")
                    }
                    is UiState.Error -> {
                        Text("Error loading facilities")
                    }
                    is UiState.Success -> {
                        val currentFacility = facilitiesList.data.firstOrNull()

                        if (currentFacility != null){
                            InfoRow(label = "Brand", value = "Canon")
                            InfoRow(label = "Model", value = "EOS R5 Mark II")
                            InfoRow(label = "Location", value = currentFacility.location)
                            InfoRow(label = "Timing", value = currentFacility.timings)
                        }
                    }
                }
            }
            AppCategoryIcon(
                painter = painterResource(R.drawable.ic_favorite),
                iconDescription = "Save Icon",
                modifier = Modifier
                    .padding(ResponsiveLayout.getResponsivePadding(2.dp, 3.dp, 4.dp))
                    .align(Alignment.TopEnd),
                iconSize = ResponsiveLayout.getResponsiveSize(20.dp, 22.dp, 24.dp),
            )
        }
    }
}

    @Composable
    fun InfoRow(label: String, value: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(12.dp, 14.dp, 16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Fixed width for label to ensure consistent layout
            Box(
                modifier = Modifier
                    .width(ResponsiveLayout.getResponsiveSize(60.dp, 70.dp, 80.dp))
                    .padding(end = ResponsiveLayout.getResponsivePadding(4.dp, 6.dp, 8.dp))
            ) {
                CustomLabel(
                    header = label,
                    modifier = Modifier.fillMaxWidth(),
                    headerColor = darkTextColor.copy(alpha = 0.5f),
                    fontSize = ResponsiveLayout.getResponsiveFontSize(14.sp, 15.sp, 16.sp)
                )
            }
            CustomLabel(
                header = value,
                modifier = Modifier.weight(1f),
                headerColor = darkTextColor.copy(alpha = 0.8f),
                fontSize = ResponsiveLayout.getResponsiveFontSize(14.sp, 15.sp, 16.sp)
            )
        }
    }


@Composable
fun InChargeCard(
    modifier: Modifier = Modifier,
    containerColor : Color = cardColor,
    shape: Shape = RectangleShape
) {
    val facilitiesViewModel : FacilitiesViewModel = koinViewModel()
    val facilitiesList = facilitiesViewModel.facilitiesState

    var expanded by remember { mutableStateOf(true) }
    val iconAlignment = if (expanded) Alignment.TopEnd else Alignment.CenterEnd

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = shape
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ResponsiveLayout.getResponsivePaddingValues(
                    horizontalPhone = 16.dp,
                    horizontalTablet = 20.dp,
                    horizontalLargeTablet = 24.dp,
                    verticalPhone = 16.dp,
                    verticalTablet = 20.dp,
                    verticalLargeTablet = 24.dp
                ))
        ) {
            if (expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(12.dp, 16.dp, 20.dp)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when(facilitiesList){
                        is UiState.Loading -> Text("Loading facilities")
                        is UiState.Error -> Text("Error loading facilities")
                        is UiState.Success -> {
                            val currentFacility = facilitiesList.data.firstOrNull()
                            if (currentFacility != null){
                                CustomLabel(
                                    header = "InCharge",
                                    headerColor = darkTextColor.copy(0.9f),
                                    fontSize = ResponsiveLayout.getResponsiveFontSize(16.sp, 18.sp, 20.sp)
                                )
                                Spacer(modifier = Modifier.height(ResponsiveLayout.getResponsivePadding(5.dp, 8.dp, 10.dp)))
                                InChargeRow(label = "Prof.", name = currentFacility.prof_incharge)
                                InChargeRow(
                                    label = "Asst.",
                                    name = currentFacility.lab_incharge,
                                    icons = listOf(R.drawable.ic_mail, R.drawable.ic_call)
                                )
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    CustomLabel(
                        header = "InCharge",
                        headerColor = darkTextColor.copy(0.9f),
                        fontSize = ResponsiveLayout.getResponsiveFontSize(16.sp, 18.sp, 20.sp)
                    )
                }
            }

            AppCategoryIcon(
                painter = painterResource(
                    if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                ),
                iconDescription = "Expand Icon",
                tint = darkTextColor,
                iconSize = ResponsiveLayout.getResponsiveSize(20.dp, 22.dp, 24.dp),
                modifier = Modifier
                    .align(iconAlignment)
                    .padding(ResponsiveLayout.getResponsivePadding(4.dp, 6.dp, 8.dp))
            )
        }
    }
}


@Composable
fun InChargeRow(
    label: String,
    name: String,
    icons: List<Int> = listOf(R.drawable.ic_mail)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(8.dp, 10.dp, 12.dp)),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Fixed width for label to ensure consistent layout
        Box(
            modifier = Modifier
                .width(ResponsiveLayout.getResponsiveSize(40.dp, 45.dp, 50.dp))
                .padding(end = ResponsiveLayout.getResponsivePadding(4.dp, 6.dp, 8.dp))
        ) {
            CustomLabel(
                header = label,
                headerColor = darkTextColor.copy(alpha = 0.5f),
                fontSize = ResponsiveLayout.getResponsiveFontSize(14.sp, 15.sp, 16.sp),
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        // Flexible content area for name and icons
        Row(
            horizontalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(16.dp, 18.dp, 20.dp)),
            modifier = Modifier
                .weight(1f)
                .padding(start = ResponsiveLayout.getResponsivePadding(4.dp, 6.dp, 8.dp))
        ) {
            CustomLabel(
                header = name,
                headerColor = darkTextColor.copy(alpha = 0.8f),
                fontSize = ResponsiveLayout.getResponsiveFontSize(14.sp, 15.sp, 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(ResponsiveLayout.getResponsivePadding(8.dp, 10.dp, 12.dp))
                    .padding(end = ResponsiveLayout.getResponsivePadding(8.dp, 10.dp, 12.dp))
            )

            icons.forEach {
                val adjustedIconSize = if (it == R.drawable.ic_call) 
                    ResponsiveLayout.getResponsiveSize(16.dp, 17.dp, 18.dp) 
                else 
                    ResponsiveLayout.getResponsiveSize(20.dp, 21.dp, 22.dp)
                
                AppCircularIcon(
                    painter = painterResource(it),
                    boxSize = ResponsiveLayout.getResponsiveSize(28.dp, 30.dp, 32.dp),
                    iconPadding = ResponsiveLayout.getResponsivePadding(4.dp, 5.dp, 6.dp),
                    iconSize = adjustedIconSize,
                    tint = highlightColor,
                    boxColor = circularBoxColor
                )
            }
        }
    }
}

@Composable
fun AdditionalInfoCard(
    modifier: Modifier = Modifier,
    containerColor : Color = cardColor
) {
    val facilitiesViewModel : FacilitiesViewModel = koinViewModel()
    val facilitiesList = facilitiesViewModel.facilitiesState

    var expanded by remember { mutableStateOf(true) }
    val iconAlignment = if (expanded) Alignment.TopEnd else Alignment.CenterEnd

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ResponsiveLayout.getResponsivePaddingValues(
                    horizontalPhone = 16.dp,
                    horizontalTablet = 20.dp,
                    horizontalLargeTablet = 24.dp,
                    verticalPhone = 16.dp,
                    verticalTablet = 20.dp,
                    verticalLargeTablet = 24.dp
                ))
        ) {
            if (expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(12.dp, 16.dp, 20.dp)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when(facilitiesList){
                        is UiState.Loading -> Text("Loading facilities")
                        is UiState.Error -> Text("Error loading facilities")
                        is UiState.Success -> {
                            val currentFacility = facilitiesList.data.firstOrNull()
                            if (currentFacility != null){
                                CustomLabel(
                                    header = "Additional Information",
                                    headerColor = darkTextColor.copy(0.9f),
                                    fontSize = ResponsiveLayout.getResponsiveFontSize(16.sp, 18.sp, 20.sp)
                                )
                                Spacer(modifier = Modifier.height(ResponsiveLayout.getResponsivePadding(5.dp, 8.dp, 10.dp)))
                                CustomLabel(
                                    header = currentFacility.description ?: "No Description found",
                                    fontSize = ResponsiveLayout.getResponsiveFontSize(14.sp, 15.sp, 16.sp)
                                )
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    CustomLabel(
                        header = "Additional Information",
                        headerColor = darkTextColor.copy(0.9f),
                        fontSize = ResponsiveLayout.getResponsiveFontSize(16.sp, 18.sp, 20.sp)
                    )
                }
            }

            AppCategoryIcon(
                painter = painterResource(
                    if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                ),
                iconDescription = "Expand Icon",
                tint = darkTextColor,
                iconSize = ResponsiveLayout.getResponsiveSize(20.dp, 22.dp, 24.dp),
                modifier = Modifier
                    .align(iconAlignment)
                    .padding(ResponsiveLayout.getResponsivePadding(4.dp, 6.dp, 8.dp))
            )
        }
    }
}


@Composable
fun UseCard(
    modifier: Modifier = Modifier,
    containerColor: Color = cardColor
) {
    var expanded by remember { mutableStateOf(true) }
    val iconAlignment = if (expanded) Alignment.TopEnd else Alignment.CenterEnd

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ResponsiveLayout.getResponsivePaddingValues(
                    horizontalPhone = 16.dp,
                    horizontalTablet = 20.dp,
                    horizontalLargeTablet = 24.dp,
                    verticalPhone = 16.dp,
                    verticalTablet = 20.dp,
                    verticalLargeTablet = 24.dp
                ))
        ) {
            if (expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(8.dp, 10.dp, 12.dp)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomLabel(
                        header = "How to use",
                        headerColor = darkTextColor.copy(0.9f),
                        fontSize = ResponsiveLayout.getResponsiveFontSize(16.sp, 18.sp, 20.sp)
                    )
                    Spacer(modifier = Modifier.height(ResponsiveLayout.getResponsivePadding(5.dp, 8.dp, 10.dp)))
                    // Add the actual usage instructions here
                    Text("No usage instructions available") // Placeholder
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    CustomLabel(
                        header = "How to use",
                        headerColor = darkTextColor.copy(0.9f),
                        fontSize = ResponsiveLayout.getResponsiveFontSize(16.sp, 18.sp, 20.sp)
                    )
                }
            }

            AppCategoryIcon(
                painter = painterResource(
                    if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                ),
                iconDescription = "Expand Icon",
                tint = darkTextColor,
                iconSize = ResponsiveLayout.getResponsiveSize(20.dp, 22.dp, 24.dp),
                modifier = Modifier
                    .align(iconAlignment)
                    .padding(ResponsiveLayout.getResponsivePadding(4.dp, 6.dp, 8.dp))
            )
        }
    }
}

@Composable
fun ActionCard(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onBookClick: () -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ResponsiveLayout.getResponsivePadding(18.dp, 20.dp, 24.dp))
            ) {
                AppButton(
                    onClick = onEditClick,
                    containerColor = cardColor,
                    contentColor = editCardTextColor,
                    buttonText = "EDIT"
                )
                AppButton(
                    onClick = onDeleteClick,
                    containerColor = cardColor,
                    contentColor = weekendColor,
                    buttonText = "DELETE"
                )
            }

            Spacer(modifier = Modifier.height(ResponsiveLayout.getResponsivePadding(12.dp, 14.dp, 16.dp)))

            AppButton(
                onClick = onBookClick,
                buttonText = "BOOK"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductCarouselPreview() {
    val productImage = listOf(
        R.drawable.temp,
        R.drawable.temp,
        R.drawable.temp
    )
    val pagerState = rememberPagerState(pageCount = { productImage.size })
////    ProductCarousel(
////        images = productImage,
////        pagerState = pagerState
////    )
//    ProdDescScreen()
////    InChargeCard()
////    ProductDescriptionCard(modifier = Modifier)
//}
////  ProdDescScreen()
////  ProductCarousel(
////      images = productImage,
////      pagerState = pagerState
////  )
////  CollapsingCard()
}
