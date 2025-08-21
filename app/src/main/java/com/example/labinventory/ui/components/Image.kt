package com.example.labinventory.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.labinventory.R

@Composable
fun AppCategoryImage(
    painter : Painter = painterResource(R.drawable.temp),
    reviewDesc : String = "category Image",
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier
){
    Image(
        painter = painter,
        contentDescription = reviewDesc,
        contentScale = contentScale,
        modifier = modifier
    )
}



