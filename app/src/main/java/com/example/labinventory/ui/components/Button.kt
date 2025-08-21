package com.example.labinventory.ui.components

import android.R.attr.minHeight
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labinventory.R
import com.example.labinventory.ui.theme.ButtonDimensions
import com.example.labinventory.ui.theme.darkTextColor
import com.example.labinventory.ui.theme.highlightColor
import com.example.labinventory.ui.theme.navBackColor
import com.example.labinventory.ui.theme.whiteColor
import com.example.labinventory.util.pxToDp
import kotlinx.coroutines.delay


@Composable
fun AppButton(
    onClick : () -> Unit = {},
    containerColor: Color = highlightColor,
    contentColor : Color = whiteColor,
    buttonText: String,
    shape: Shape = RectangleShape,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = ButtonDimensions.ButtonHeight),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape,
        content = {
                Text(
                    text = buttonText,
                    fontSize = 16.sp,
                    color = contentColor
                )
        }
    )
}

@Composable
fun EditButton(
    onClick : () -> Unit = {},
    containerColor: Color = darkTextColor.copy(0.08f),
    contentColor : Color = highlightColor,
    buttonText: String = "Edit",
    icon: Painter = painterResource(R.drawable.ic_edit),
    iconSize: Dp = ButtonDimensions.ButtonIcon,
    shape: Shape = RoundedCornerShape(ButtonDimensions.ButtonCorner),
    modifier: Modifier = Modifier
){
  Button(
      onClick = onClick,
      modifier = modifier.defaultMinSize(minHeight = 0.dp),
      colors = ButtonDefaults.buttonColors(
          containerColor = containerColor,
          contentColor = contentColor
      ),
      contentPadding = PaddingValues(0.dp),
      shape = shape,
  ) {
      Row(
          modifier = Modifier.padding(horizontal = ButtonDimensions.MediumPadding, vertical = 0.dp),
          horizontalArrangement = Arrangement.spacedBy(ButtonDimensions.SmallPadding),
      ) {
          Icon(
              painter = icon,
              contentDescription = "Edit Icon",
              tint = contentColor,
              modifier = Modifier.size(iconSize)
          )
          CustomLabel(
              header = buttonText,
              headerColor = contentColor,
              fontSize = 10.sp
          )
      }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppFAB(
    showIntro : Boolean = true,
    delay: Int = 5000,
    onClick : () -> Unit ={},
    contentColor: Color = whiteColor,
    containerColor: Color = navBackColor,
    capsuleShape: Shape = RoundedCornerShape(ButtonDimensions.FabCorner),
    iconShape: Shape = CircleShape,
){
    var showLabel by rememberSaveable { mutableStateOf(showIntro) }

    val ai_chatIcon = painterResource(R.drawable.ic_aichat)


    LaunchedEffect(showLabel) {
        delay(delay.toLong())
        showLabel = false
    }

    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .wrapContentWidth(),
        containerColor = containerColor,
        contentColor = contentColor,
        shape = if (showLabel) capsuleShape else iconShape,

    ) {
        AnimatedContent(
            targetState = showLabel,
            transitionSpec = {
                fadeIn(tween(3000)) + scaleIn() with fadeOut(tween(3000)) + scaleOut()
            },
            label = "FAB animation"
        ) {targetState ->
            if (targetState) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = ButtonDimensions.LargePadding, vertical = ButtonDimensions.MediumPadding),
                    horizontalArrangement = Arrangement.spacedBy(ButtonDimensions.FABPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = ai_chatIcon,
                        contentDescription = "FAB Icon",
                        tint = Color.Unspecified
                    )
                    CustomLabel(
                        header = "Share your project to get equipment suggestions",
                        headerColor = contentColor,
                        fontSize = 14.sp
                    )
                }
            } else {
                Icon(
                    painter = ai_chatIcon,
                    contentDescription = "FAB Icon",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

