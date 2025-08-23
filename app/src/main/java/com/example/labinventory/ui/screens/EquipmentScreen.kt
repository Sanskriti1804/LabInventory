package com.example.labinventory.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.labinventory.R
import com.example.labinventory.data.model.EquipmentCategory
import com.example.labinventory.data.model.UiState
import com.example.labinventory.data.model.categories
import com.example.labinventory.data.schema.Facilities
import com.example.labinventory.navigation.Screen
import com.example.labinventory.ui.components.AppCategoryIcon
import com.example.labinventory.ui.components.AppCircularIcon
import com.example.labinventory.ui.components.AppSearchBar
import com.example.labinventory.ui.components.CustomLabel
import com.example.labinventory.ui.components.CustomNavigationBar
import com.example.labinventory.ui.components.CustomTopBar
import com.example.labinventory.ui.components.ResponsiveColumn
import com.example.labinventory.ui.components.ResponsiveSpacer
import com.example.labinventory.ui.theme.EquipmentScreenDimensions
import com.example.labinventory.ui.theme.cardColor
import com.example.labinventory.ui.theme.categoryColor
import com.example.labinventory.ui.theme.categoryIconColor
import com.example.labinventory.ui.theme.darkTextColor
import com.example.labinventory.ui.theme.highlightColor
import com.example.labinventory.ui.theme.lightTextColor
import com.example.labinventory.ui.theme.navLabelColor
import com.example.labinventory.ui.theme.whiteColor
import com.example.labinventory.util.pxToDp
import com.example.labinventory.util.ResponsiveDimensions
import com.example.labinventory.viewmodel.FacilitiesViewModel
import com.example.labinventory.viewmodel.FilterSortViewModel
import com.example.labinventory.viewmodel.ItemsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipmentScreen(
    navController: NavHostController,
    filterSortViewModel: FilterSortViewModel = koinViewModel(),
    itemViewModel: ItemsViewModel = koinViewModel(),
    facilitiesViewModel: FacilitiesViewModel = koinViewModel(),
    categoryName : String
){
    var isFilterSheetVisible by filterSortViewModel.isSheetVisible
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var isSaved by remember { mutableStateOf(false) }

    val items = itemViewModel.itemsState
    val facilitiesState = facilitiesViewModel.facilitiesState

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopBar(
                title = categoryName,
                onNavigationClick = {
                    navController.popBackStack()
                }
           )
        },
        containerColor = whiteColor,
        bottomBar = {
            CustomNavigationBar(
                navController = navController
            )
        },
    ) { paddingValues ->
        ResponsiveColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Top Search Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = ResponsiveDimensions.getScreenPadding(),
                        end = ResponsiveDimensions.getScreenPadding(),
                        top = ResponsiveDimensions.getScreenPadding(),
                        bottom = ResponsiveDimensions.getScreenPadding() * 2
                    )
            ) {
                AppSearchBar(
                    query = "",
                    onQueryChange = {},
                    modifier = Modifier
                        .height(ResponsiveDimensions.getSearchBarHeight())
                        .weight(1f),
                    placeholder = "Equipments, Tools, Supplies, etc..."
                )

                Spacer(modifier = Modifier.width(ResponsiveDimensions.getScreenPadding() / 2))

                AppCircularIcon(
                    onClick = { filterSortViewModel.showSheet() }
                )
            }

            CategoryRow(categories = categories)

            ResponsiveSpacer()

            when (items) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is UiState.Success -> {

                    val facilities = (facilitiesState as? UiState.Success)?.data ?: emptyList()

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(ResponsiveDimensions.getGridColumns()),
                        contentPadding = PaddingValues(horizontal = ResponsiveDimensions.getScreenPadding()),
                        verticalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getGridSpacing()),
                        horizontalArrangement = Arrangement.spacedBy(ResponsiveDimensions.getGridSpacing()),
                    ) {
                        items(items.data) { item ->
                            EquipmentCard(
                                image = item.image_url,
                                equipName = item.name,
                                available = if (item.is_available == true) "Available" else "Not Available",
                                onClick = { navController.navigate(Screen.ProductDescriptionScreen.route) },
                                saveClick = {
                                    isSaved = !isSaved
                                },
                                facilityName = itemViewModel.getFacilityNameForEquipment(item, facilities)
                            )
                        }
                    }
                }

                is UiState.Error -> {
                    val errorMessage = items.exception.localizedMessage ?: "Something went wrong"
                    Log.e("EquipmentScreen", "Error loading items", items.exception)

                    Text(
                        text = "Error loading items: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier.padding(ResponsiveDimensions.getCardPadding())
                    )
                }
            }
        }

        if (isFilterSheetVisible){
            FilterSortBottomSheet(viewModel = filterSortViewModel)
        }
    }
}

@Composable
fun CategoryItem(
    category: EquipmentCategory,
    isSelected : Boolean = false,
    selectedIconColor: Color = highlightColor,
    iconColor: Color = categoryIconColor,
    selectedLabelColor: Color = categoryColor,
    labelColor: Color = darkTextColor.copy(0.4f),
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable{
            onClick()
        }
    ) {
        AppCategoryIcon(
            painter = painterResource(id = category.categoryImage),
            iconDescription = category.label,
            tint = if (isSelected) selectedIconColor else iconColor
        )

        Spacer(modifier = Modifier.height(EquipmentScreenDimensions.CategoryItemSpacer))

        CustomLabel(
            header = category.label,
            fontSize = 10.sp,
            modifier = Modifier,
            headerColor = if (isSelected) selectedLabelColor else labelColor
        )
    }
}

@Composable
fun CategoryRow(categories: List<EquipmentCategory>) {
    var selectedCategoryId by remember { mutableIntStateOf(categories.first().id) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(
            start = EquipmentScreenDimensions.CategoryRowStartPadding,
            end = EquipmentScreenDimensions.CategoryRowEndPadding,
            top = EquipmentScreenDimensions.CategoryRowTopPadding,
            bottom = EquipmentScreenDimensions.CategoryRowBottomPadding
        ),
        horizontalArrangement = Arrangement.spacedBy(EquipmentScreenDimensions.CategoryRowItemSpacing),
        modifier = Modifier.height(EquipmentScreenDimensions.CategoryRowHeight)
    ) {
        itemsIndexed(categories) { index, category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    selectedCategoryId = category.id
                    coroutineScope.launch {
                        listState.animateScrollToItem(index)
                    }
                }
            ) {
                CategoryItem(
                    category = category,
                    isSelected = category.id == selectedCategoryId,
                    onClick = {  }
                )

                Spacer(modifier = Modifier.height(EquipmentScreenDimensions.CategoryItemSpacer))

                if (category.id == selectedCategoryId) {
                    Box(
                        Modifier
                            .width(EquipmentScreenDimensions.CategoryUnderlineWidth)
                            .height(EquipmentScreenDimensions.CategoryUnderlineHeight)
                            .background(categoryColor)
                    )
                }
            }
        }
    }
}


@Composable
fun EquipmentCard(
    image : Any,
    available : String,
    equipName : String,
    onClick: () -> Unit = {},
    shape: Shape = RectangleShape,
    imageHeight: Dp = EquipmentScreenDimensions.EquipmentImageHeight,
    detailHeight: Dp = EquipmentScreenDimensions.EquipmentDetailHeight,
    isSaved : Boolean = false,
    saveClick : () -> Unit = {},
    facilityName : String
) {
    val itemViewModel : ItemsViewModel = koinViewModel()

    Card(
        modifier = Modifier
            .padding(
                top = EquipmentScreenDimensions.EquipmentCardTopPadding,
                bottom = EquipmentScreenDimensions.EquipmentCardBottomPadding,
                start = EquipmentScreenDimensions.EquipmentCardStartPadding,
                end = EquipmentScreenDimensions.EquipmentCardEndPadding
            ),
        onClick = onClick,
        shape = shape
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .background(cardColor)
                    .height(imageHeight)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Equipment Image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(EquipmentScreenDimensions.EquipmentImageInnerPadding),
                    contentScale = ContentScale.Crop
                )
                AppCategoryIcon(
                    painter = painterResource(R.drawable.ic_save),
                    iconDescription = "Save icon",
                    iconSize = EquipmentScreenDimensions.SaveIconSize,
                    tint = if (isSaved) highlightColor else navLabelColor,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(EquipmentScreenDimensions.SaveIconPadding)
                        .clickable{
                            saveClick()
                        }
                )
            }

            Column(
                modifier = Modifier
                    .height(detailHeight)
                    .fillMaxWidth()
                    .background(whiteColor)
                    .padding(top = EquipmentScreenDimensions.DetailTopPadding),
                horizontalAlignment = Alignment.Start
            ) {
                CustomLabel(
                    header = equipName,
                    headerColor = darkTextColor,
                    fontSize = EquipmentScreenDimensions.LabelFontSize.value.sp,
                    modifier = Modifier.padding(top = EquipmentScreenDimensions.LabelTopPadding)
                )

                CustomLabel(
                    header =  available,
                    headerColor = highlightColor,
                    fontSize = EquipmentScreenDimensions.LabelFontSize.value.sp,
                    modifier = Modifier.padding(
                        top = EquipmentScreenDimensions.LabelAvailableTopPadding,
                        bottom = EquipmentScreenDimensions.LabelAvailableBottomPadding
                    )
                )

                CustomLabel(
                    header = facilityName,
                    headerColor = lightTextColor,
                    fontSize = EquipmentScreenDimensions.LabelFontSize.value.sp,
                    modifier = Modifier.padding(bottom = EquipmentScreenDimensions.LabelFacilityBottomPadding)
                )

                Row {
                    AppCategoryIcon(
                        painter = painterResource(R.drawable.ic_location),
                        iconDescription = "location icon",
                        iconSize = EquipmentScreenDimensions.LocationIconSize,
                        tint = lightTextColor
                    )
                    Spacer(modifier = Modifier.width(EquipmentScreenDimensions.LocationSpacerWidth))
                    CustomLabel(
                        header = "IDC, Photo Studio",
                        headerColor = lightTextColor,
                        fontSize = EquipmentScreenDimensions.LabelFontSize.value.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EquipmentScreenPreview() {
    val navController : NavHostController = rememberNavController()
//    EquipmentScreen(navController)
//    EquipmentCard()
//    CategoryRow(categories = categories)
}


