package com.example.labinventory.ui.screens

import androidx.compose.foundation.LocalIndication
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.labinventory.R
import com.example.labinventory.data.model.UiState
import com.example.labinventory.data.model.UserRole
import com.example.labinventory.navigation.Screen
import com.example.labinventory.ui.components.AppButton
import com.example.labinventory.ui.components.AppCategoryIcon
import com.example.labinventory.ui.components.AppCircularIcon
import com.example.labinventory.ui.components.CustomLabel
import com.example.labinventory.ui.components.CustomTopBar
import com.example.labinventory.ui.theme.ProdDescScreenDimensions
import com.example.labinventory.ui.theme.cardColor
import com.example.labinventory.ui.theme.circularBoxColor
import com.example.labinventory.ui.theme.darkTextColor
import com.example.labinventory.ui.theme.editCardTextColor
import com.example.labinventory.ui.theme.highlightColor
import com.example.labinventory.ui.theme.weekendColor
import com.example.labinventory.ui.theme.whiteColor
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
                    modifier = Modifier.padding(ProdDescScreenDimensions.BottomButtonPadding)
                )
            }
            else{
                ActionCard(
                    onEditClick = {},
                    onDeleteClick = {},
                    onBookClick = {},
                    modifier = Modifier.padding(ProdDescScreenDimensions.BottomButtonPadding)
                )
            }
        },
        containerColor = whiteColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(ProdDescScreenDimensions.ScreenPadding),
            verticalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.SectionSpacing)
        ) {
            Spacer(modifier = Modifier.height(ProdDescScreenDimensions.TopSpacerHeight))

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
    indicatorSize : Dp = ProdDescScreenDimensions.CarouselIndicatorSize,
    isFav : Boolean = false
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(ProdDescScreenDimensions.CarouselHeight),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(ProdDescScreenDimensions.CarouselSpacing)
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
            Spacer(modifier = Modifier.height(ProdDescScreenDimensions.CarouselSpacing))
            HorizontalPagerIndicator(
                pagerState = pagerState, // Use the passed pagerState
                pageCount = images.size,
                inactiveColor = inactiveColor,
                activeColor = activeColor,
                indicatorShape = indicatorShape,
                modifier = Modifier
                    .size(indicatorSize)
            )
            Spacer(modifier = Modifier.height(ProdDescScreenDimensions.CarouselBottomSpacer))
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
            .height(ProdDescScreenDimensions.ProductCardHeight),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = cardContainerColor
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ProdDescScreenDimensions.ProductCardPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.ProductCardSpacing)
            ) {
                CustomLabel(
                    header = "Canon EOS R50 V",
                    headerColor = darkTextColor,
                    fontSize = 16.sp,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(ProdDescScreenDimensions.ProductCardSpacingHeight))

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
                    .padding(ProdDescScreenDimensions.FavIconPadding)
                    .align(Alignment.TopEnd),
                iconSize = ProdDescScreenDimensions.FavIconSize,
            )
        }
    }
}

    @Composable
    fun InfoRow(label: String, value: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.InfoRowSpacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomLabel(
                header = label,
                modifier = Modifier.weight(0.2f),
                headerColor = darkTextColor.copy(alpha = 0.5f),
                fontSize = ProdDescScreenDimensions.InfoRowFontSize
            )
            CustomLabel(
                header = value,
                modifier = Modifier.weight(1f),
                headerColor = darkTextColor.copy(alpha = 0.8f),
                fontSize = ProdDescScreenDimensions.InfoRowFontSize
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
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        shape = shape
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ProdDescScreenDimensions.CardPadding)
        ) {
            if (expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.CardInnerSpacing),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when(facilitiesList){
                        is UiState.Loading -> {
                            Text("Loading facilities")
                        }
                        is UiState.Error -> {
                            Text("Error loading facilities")
                        }
                        is UiState.Success ->{
                            val currentFacility = facilitiesList.data.firstOrNull()
                            if (currentFacility != null){
                                CustomLabel(
                                    header = "InCharge",
                                    headerColor = darkTextColor.copy(0.9f),
                                    fontSize = ProdDescScreenDimensions.CardHeaderFontSize,
                                    modifier = Modifier
                                )
                                Spacer(modifier = Modifier.height(pxToDp(5)))

                                InChargeRow(label = "Prof.",
                                    name = currentFacility.prof_incharge)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ProdDescScreenDimensions.CardCollapsedHeight),
                    contentAlignment = Alignment.CenterStart
                ) {
                    CustomLabel(
                        header = "InCharge",
                        headerColor = darkTextColor.copy(0.9f),
                        fontSize = ProdDescScreenDimensions.CardHeaderFontSize,
                        modifier = Modifier
                    )
                }
            }


            AppCategoryIcon(
                painter = painterResource(
                    if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                ),
                iconDescription = "Expand Icon",
                tint = darkTextColor,
                iconSize = ProdDescScreenDimensions.CardIconSize,
                modifier = Modifier
                    .align(iconAlignment)
                    .padding(ProdDescScreenDimensions.CardIconPadding)
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
        horizontalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.InChargePadding),
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomLabel(
            header = label,
            headerColor = darkTextColor.copy(alpha = 0.5f),
            fontSize = 14.sp,
            modifier = Modifier.weight(0.2f)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.CardRowSpacing),
            modifier = Modifier
                .weight(0.9f)
        ) {
            CustomLabel(
                header = name,
                headerColor = darkTextColor.copy(alpha = 0.8f),
                fontSize = ProdDescScreenDimensions.CardSubHeaderFontSize,
                modifier = Modifier.padding(ProdDescScreenDimensions.InChargeNamePadding)
            )

            icons.forEach {
                AppCircularIcon(
                    painter = painterResource(it),
                    boxSize = ProdDescScreenDimensions.InChargeIconBoxSize,
                    iconPadding = ProdDescScreenDimensions.InChargeIconPadding,
                    iconSize = ProdDescScreenDimensions.InChargeIconSize,
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
            .clickable { expanded = !expanded }, // collapsible behavior
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ProdDescScreenDimensions.CardPadding)
        ) {
            if (expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.CardInnerSpacing),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when(facilitiesList){
                        is UiState.Loading -> {
                            Text("Loading facilities")
                        }
                        is UiState.Error -> {
                            Text("Error loading facilities")
                        }
                        is UiState.Success ->{
                            val currentFacility = facilitiesList.data.firstOrNull()
                            if (currentFacility != null){
                                CustomLabel(
                                    header = "InCharge",
                                    headerColor = darkTextColor.copy(0.9f),
                                    fontSize = ProdDescScreenDimensions.CardHeaderFontSize,
                                    modifier = Modifier
                                )

                                CustomLabel(
                                    header = currentFacility.description ?: "No Description found"
                                )
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ProdDescScreenDimensions.CardCollapsedHeight),
                    contentAlignment = Alignment.CenterStart
                ) {
                    CustomLabel(
                        header = "Additional Information",
                        headerColor = darkTextColor.copy(0.9f),
                        fontSize = ProdDescScreenDimensions.CardHeaderFontSize,
                        modifier = Modifier
                    )
                }
            }


            AppCategoryIcon(
                painter = painterResource(
                    if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                ),
                iconDescription = "Expand Icon",
                tint = darkTextColor,
                iconSize = ProdDescScreenDimensions.InChargeIconSize,
                modifier = Modifier
                    .align(iconAlignment)
                    .padding(ProdDescScreenDimensions.InChargeIconPadding)
            )
        }
    }
}


@Composable
fun UseCard(
    modifier: Modifier = Modifier,
    containerColor : Color = cardColor
) {
    var expanded by remember { mutableStateOf(true) }
    val iconAlignment = if (expanded) Alignment.TopEnd else Alignment.CenterEnd

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }, // collapsible behavior
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (!expanded) Modifier.height(ProdDescScreenDimensions.CardCollapsedHeight) else Modifier
                )
                .padding(
                    horizontal = ProdDescScreenDimensions.CardPadding,
                    vertical = ProdDescScreenDimensions.CardPadding / 2
                ),
            verticalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.CardInnerSpacing)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomLabel(
                    header = "How to use",
                    headerColor = darkTextColor.copy(0.9f),
                    fontSize = ProdDescScreenDimensions.CardHeaderFontSize,
                    modifier = Modifier.weight(1f)
                )

                AppCategoryIcon(
                    painter = painterResource(
                        if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                    ),
                    iconDescription = "Expand Icon",
                    tint = darkTextColor,
                    iconSize = ProdDescScreenDimensions.CardIconSize,
                    modifier =  Modifier.padding(ProdDescScreenDimensions.CardIconPadding)
                )
            }

            if (expanded) {

            }
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
        Column(
            verticalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.ActionVerticalSpacing)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ProdDescScreenDimensions.ActionButtonSpacing)
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

            Spacer(modifier = Modifier.height(ProdDescScreenDimensions.ActionVerticalSpacing))

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
