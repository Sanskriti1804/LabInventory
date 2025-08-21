package com.example.labinventory.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


object ButtonDimensions {
    val ButtonHeight = 52.dp
    val ButtonCorner = 20.dp
    val ButtonIcon = 12.dp

    val SmallPadding = 6.dp
    val MediumPadding = 10.dp
    val LargePadding = 18.dp
    val FABPadding = 12.dp
    val FabCorner = 30.dp
}

object IconDimensions {
    val CircularBox = 46.dp
    val CircularPadding = 11.dp
    val CircularSize = 22.dp

    val Small = 16.dp
    val Medium = 24.dp
    val Large = 32.dp
}

object NavBarDimensions {
    // Bar
    val Height = 101.dp
    val DividerHeight = 0.5.dp

    // Item paddings
    val ItemTop = 23.dp
    val ItemBottom = 29.dp
    val ItemHorizontal = 10.dp

    // Spacing
    val IconSize = 24.dp
    val LabelSpacing = 8.dp

    // Badge
    val BadgeOffsetX = 10.dp
    val BadgeOffsetY = (-6).dp
    val BadgePaddingHorizontal = 4.dp
    val BadgePaddingVertical = 1.dp
    val BadgeFontSize = 10.sp

    // Label
    val LabelFontSize = 14.sp
    val LabelTopPadding = 3.dp
}

object SearchBarDimensions {
    // Container
    val Height = 46.dp
    val CornerRadius = 50.dp
    val HorizontalPadding = 16.dp
    val VerticalPadding = 8.dp

    // Icon
    val IconSize = 21.dp
    val IconTextSpacing = 16.dp

    // Text
    val FontSize = 15.sp
}

object TextFieldDimensions {
    val CornerRadius = 4.dp
    val Padding = 4.dp
    val IconPadding = 8.dp
    val PlaceholderPadding = 4.dp
}

object TypographyDimensions {
    // Titles
    val TitleFontSize: TextUnit = 20.sp
    val TitleMaxLines: Int = 1

    // Labels
    val SmallLabelFontSize: TextUnit = 14.sp
    val LabelFontSize: TextUnit = 16.sp
    val LabelMaxLines: Int = 1

    // Divider
    val DividerThickness: Dp = 1.dp
    val DividerPadding: Dp = 12.dp
}

object TopBarDimensions {
    // Container
    val TopPadding: Dp = 15.dp
    val HorizontalPadding: Dp = 16.dp

    // Navigation icon
    val NavIconStartPadding: Dp = 17.dp
    val NavIconEndPadding: Dp = 11.dp
    val NavIconTopPadding: Dp = 20.dp

    // Title
    val TitleTopPadding: Dp = 17.dp
}

object BookingScreenDimensions {
    // General screen padding
    val ScreenPadding: Dp = 16.dp
    val TopSpacerHeight: Dp = 27.dp

    // Tab selector
    val TabSpacing: Dp = 12.dp
    val TabPadding: Dp = 8.dp
    val TabIconSpacing: Dp = 10.dp
    val TabIconSize: Dp = 20.dp

    // InfoCard
    val CardHorizontalPadding: Dp = 20.dp
    val CardVerticalPadding: Dp = 22.dp
    val CardContentSpacing: Dp = 20.dp

    // Product info row
    val ProductImageSize: Dp = 76.dp
    val ProductImageTextSpacing: Dp = 30.dp
    val ProductInfoSpacing: Dp = 9.dp
    val ProductTitleFontSize: TextUnit = 16.sp
    val ProductLocationFontSize: TextUnit = 12.sp
    val ProductStatusFontSize: TextUnit = 14.sp
    val StatusCornerRadius: Dp = 20.dp
    val StatusHorizontalPadding: Dp = 10.dp
    val StatusVerticalPadding: Dp = 4.dp
    val StatusTopBottomPadding: Dp = 2.dp

    // Divider
    val DividerThickness: Dp = 1.dp

    // InCharge section
    val InChargeSpacing: Dp = 16.dp
    val InChargeLabelWidth: Dp = 50.dp
    val InChargeRowIconSpacing: Dp = 8.dp

    // Booking dates
    val BookingDatesSpacing: Dp = 18.dp
    val BookingDatesBottomPadding: Dp = 2.dp
    val BookingDatesLabelWidth: Dp = 80.dp
    val BookingDatesFontSize: TextUnit = 16.sp
}

object CalendarDimensions {
    // Screen
    val ScreenPadding: Dp = 16.dp
    val ColumnSpacing: Dp = 20.dp
    val SmallSpacerHeight: Dp = 10.dp

    // Month Tabs
    val MonthTabHorizontalSpacing: Dp = 12.dp
    val MonthTabCornerRadius: Dp = 4.dp
    val MonthTabHorizontalPadding: Dp = 45.dp
    val MonthTabVerticalPadding: Dp = 12.dp

    // Days of Week
    val DayOfWeekSpacing: Dp = 44.dp

    // Calendar Grid
    val GridVerticalSpacing: Dp = 4.dp
    val GridHorizontalSpacing: Dp = 4.dp
    val TodayBorderWidth: Dp = 1.dp

    // Status Card
    val StatusCardPadding: Dp = 16.dp
    val StatusLabelSpacerHeight: Dp = 18.dp
    val StatusLabelBoxHorizontalPadding: Dp = 10.dp
    val StatusLabelBoxVerticalPadding: Dp = 2.dp

    // EquipBookingCard
    val EquipCardPadding: Dp = 20.dp
    val EquipImageSize: Dp = 76.dp
    val EquipImageSpacer: Dp = 30.dp
    val EquipItemSpacing: Dp = 9.dp
}

object ChatBottomSheetDimensions {
    // Container
    val CornerRadius: Dp = 30.dp
    val MinHeight: Dp = 880.dp
    val HorizontalPadding: Dp = 15.dp

    // Icons
    val ChatIconSize: Dp = 44.dp
    val CloseIconSize: Dp = 26.dp

    // Spacing
    val TitleSpacerHeight: Dp = 12.dp
    val FieldSpacerHeight: Dp = 24.dp

    // Input field
    val InputVerticalPadding: Dp = 8.dp
}

object EquipmentScreenDimensions {
    // Top Search Row
    val SearchRowStartPadding: Dp = 14.dp
    val SearchRowEndPadding: Dp = 14.dp
    val SearchRowTopPadding: Dp = 19.dp
    val SearchRowBottomPadding: Dp = 37.dp
    val SearchBarHeight: Dp = 46.dp
    val SearchBarSpacerWidth: Dp = 8.dp

    // Category Row
    val CategoryRowStartPadding: Dp = 28.dp
    val CategoryRowEndPadding: Dp = 28.dp
    val CategoryRowTopPadding: Dp = 12.dp
    val CategoryRowBottomPadding: Dp = 8.dp
    val CategoryRowItemSpacing: Dp = 37.dp
    val CategoryRowHeight: Dp = 64.dp
    val CategoryLabelSpacer: Dp = 8.dp
    val CategoryUnderlineWidth: Dp = 30.dp
    val CategoryUnderlineHeight: Dp = 1.dp

    // CategoryItem
    val CategoryItemSpacer: Dp = 8.dp

    // Grid
    val GridHorizontalPadding: Dp = 16.dp
    val GridVerticalSpacing: Dp = 13.dp
    val GridHorizontalSpacing: Dp = 13.dp

    // EquipmentCard
    val EquipmentCardTopPadding: Dp = 12.dp
    val EquipmentCardPadding: Dp = 16.dp
    val EquipmentCardBottomPadding: Dp = 17.dp
    val EquipmentCardStartPadding: Dp = 17.dp
    val EquipmentCardEndPadding: Dp = 12.dp
    val EquipmentImageHeight: Dp = 191.dp
    val EquipmentDetailHeight: Dp = 69.dp
    val EquipmentImageInnerPadding: Dp = 12.dp
    val SaveIconSize: Dp = 18.dp
    val SaveIconPadding: Dp = 8.dp
    val LabelFontSize: Dp = 12.dp
    val LabelTopPadding: Dp = 2.dp
    val LabelAvailableTopPadding: Dp = 3.dp
    val LabelAvailableBottomPadding: Dp = 3.dp
    val LabelFacilityBottomPadding: Dp = 3.dp
    val DetailTopPadding: Dp = 6.dp

    // Location Row
    val LocationIconSize: Dp = 12.dp
    val LocationSpacerWidth: Dp = 5.dp
}

object FilterSortDimensions {

    // Sheet
    val SheetCornerRadius: Dp = 10.dp
    val SheetPadding: Dp = 16.dp
    val SheetTopSpacer: Dp = 18.dp
    val SectionSpacer: Dp = 14.dp
    val BottomSpacer: Dp = 24.dp

    // Tabs
    val TabHorizontalPadding: Dp = 16.dp
    val TabIndicatorHeight: Dp = 1.dp
    val TabIndicatorCornerRadius: Dp = 2.dp
    val TabTextSize: TextUnit = 20.sp

    // Filter Section
    val FilterSectionVerticalPadding: Dp = 14.dp
    val FilterSectionSpacer: Dp = 16.dp
    val FilterLabelTextSize: TextUnit = 14.sp

    // Chip Group
    val ChipHorizontalSpacing: Dp = 12.dp
    val ChipVerticalSpacing: Dp = 12.dp
    val ChipCornerRadius: Dp = 4.dp
    val ChipBorderWidth: Dp = 1.dp
    val ChipTextSize: TextUnit = 12.sp
    val ChipHorizontalPadding: Dp = 9.dp
    val ChipVerticalPadding: Dp = 6.dp

    // Sort List
    val SortItemVerticalPadding: Dp = 26.dp
    val SortItemHorizontalPadding: Dp = 19.dp
    val SortDividerHorizontalPadding: Dp = 16.dp
    val SortItemTextSize: TextUnit = 14.sp

    // Buttons Row
    val ButtonHorizontalSpacing: Dp = 8.dp
}

object HomeScreenDimensions {
    // Search Row
    val SearchRowStartPadding: Dp = 14.dp
    val SearchRowEndPadding: Dp = 14.dp
    val SearchRowTopPadding: Dp = 19.dp
    val SearchRowBottomPadding: Dp = 37.dp

    // Search Bar
    val SearchBarHeight: Dp = 46.dp
    val SearchBarIconSpacing: Dp = 8.dp

    // Section Label
    val SectionLabelStartPadding: Dp = 16.dp
    val SectionLabelBottomPadding: Dp = 13.dp
    val SectionLabelFontSize: Dp = 20.dp

    // Grid
    val GridHorizontalPadding: Dp = 16.dp
    val GridVerticalSpacing: Dp = 13.dp
    val GridHorizontalSpacing: Dp = 13.dp

    // Category Card
    val CategoryCardHeight: Dp = 110.dp
    val CategoryCardPadding: Dp = 16.dp
    val CategoryCardFontSize: Dp = 16.dp
}


object NewEquipmentDimensions {
    // Screen
    val ScreenPadding: Dp = 16.dp
    val FieldSpacing: Dp = 13.dp
    val BottomButtonPadding: Dp = 1.dp

    // Image Card
    val ImageCardHeight: Dp = 108.dp
    val ImageCardCornerRadius: Dp = 4.dp
    val ImageIconSize: Dp = 37.dp
    val ImageCardSpacerHeight: Dp = 8.dp
    val ImageCardLabelSize: TextUnit = 12.sp

    // Dropdown Row
    val DropDownRowSpacing: Dp = 4.dp
}

object ProdDescScreenDimensions {
    // Screen
    val ScreenPadding: Dp = 16.dp
    val SectionSpacing: Dp = 12.dp
    val TopSpacerHeight: Dp = 20.dp
    val BottomButtonPadding: Dp = 16.dp

    // Carousel
    val CarouselHeight: Dp = 200.dp
    val CarouselIndicatorSize: Dp = 6.dp
    val CarouselSpacing: Dp = 16.dp
    val CarouselBottomSpacer: Dp = 3.dp

    // Product Description Card
    val ProductCardHeight: Dp = 190.dp
    val ProductCardPadding: Dp = 16.dp
    val ProductCardSpacing: Dp = 16.dp
    val ProductCardSpacingHeight: Dp = 3.dp
    val ProductTitleFontSize: TextUnit = 16.sp
    val InfoRowSpacing: Dp = 12.dp
    val InfoRowFontSize: TextUnit = 14.sp
    val FavIconSize: Dp = 20.dp
    val FavIconPadding: Dp = 2.dp

    // InCharge & Additional Info Cards
    val CardPadding: Dp = 16.dp
    val CardCollapsedHeight: Dp = 52.dp
    val CardInnerSpacing: Dp = 12.dp
    val CardHeaderFontSize: TextUnit = 16.sp
    val CardSubHeaderFontSize: TextUnit = 14.sp
    val CardIconSize: Dp = 20.dp
    val CardIconPadding: Dp = 4.dp
    val CardRowSpacing: Dp = 16.dp
    val InChargeIconBoxSize: Dp = 28.dp
    val InChargeIconSize: Dp = 20.dp
    val InChargeIconPadding: Dp = 4.dp
    val InChargeNamePadding: Dp = 10.dp
    val InChargePadding: Dp = 10.dp

    // Action Card
    val ActionButtonSpacing: Dp = 18.dp
    val ActionVerticalSpacing: Dp = 12.dp
}

object ProjectInfoDimensions {
    // Paddings
    val screenPadding: Dp = 16.dp
    val topSpacerHeight: Dp = 20.dp
    val verticalSpacing: Dp = 13.dp
    val bottomSpacerHeight: Dp = 13.dp

    // Text
    val descriptionFontSize = 16.sp
    val descriptionLineHeight = 20.sp

    // Button
    val buttonPadding: Dp = 16.dp
}


















