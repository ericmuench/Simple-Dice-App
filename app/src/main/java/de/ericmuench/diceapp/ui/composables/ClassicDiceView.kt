package de.ericmuench.diceapp.ui.composables

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.ericmuench.diceapp.R
import de.ericmuench.diceapp.util.isOdd


@Composable
fun ClassicDiceView(
    displayNumber: Int,
    modifier : Modifier = Modifier,
    onClick: () -> Unit = { },
    diceBackgroundColor : Color = MaterialTheme.colors.primary,
    diceDotsColor : Color = MaterialTheme.colors.background
) {
    val interactionSource = remember { MutableInteractionSource() }

    var rotationState by remember {
        mutableStateOf(0f)
    }

    val canvasRotation by animateFloatAsState(rotationState, animationSpec = spring(Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessVeryLow))

    Column(
        modifier
            .clickable(
                onClick = {
                    rotationState = (rotationState + 360) % 3600
                    onClick.invoke()
                },
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        if(displayNumber in 1..6){
            val fillFraction = 0.65f
            val canvasModifier =
                if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    Modifier.fillMaxHeight(fillFraction)
                }
                else {
                    Modifier.fillMaxWidth(fillFraction)
                }
            Canvas(
                modifier = canvasModifier
                    .align(Alignment.CenterHorizontally)
                    .aspectRatio(1f)
                    .padding(8.dp)
                    .rotate(canvasRotation)
                    //.background(diceBackgroundColor, shape = RoundedCornerShape(8.dp))
            ){
                val dotRadius = size.width/15
                val dotOffset = size.width/2 - dotRadius * 2

                drawRoundRect(color = diceBackgroundColor, cornerRadius = CornerRadius(32f,32f))

                if(displayNumber.isOdd()){
                    //draw center point
                    drawCircle(color = diceDotsColor, radius = dotRadius)
                }

                if(displayNumber > 1){
                    //draw points at left upper corner and points at right lower corner
                    drawCircle(
                        color = diceDotsColor,
                        radius = dotRadius,
                        center = center.plus(Offset( dotOffset,dotOffset))
                    )

                    drawCircle(
                        color = diceDotsColor,
                        radius = dotRadius,
                        center = center.minus(Offset( dotOffset,dotOffset))
                    )
                }

                if(displayNumber > 3){
                    //draw points at right upper corner and points at left lower corner
                    drawCircle(
                        color = diceDotsColor,
                        radius = dotRadius,
                        center = center.plus(Offset( dotOffset,-1*dotOffset))
                    )

                    drawCircle(
                        color = diceDotsColor,
                        radius = dotRadius,
                        center = center.minus(Offset(dotOffset,-1*dotOffset))
                    )
                }

                if(displayNumber == 6){
                    // draw Points at the center left and center right
                    drawCircle(
                        color = diceDotsColor,
                        radius = dotRadius,
                        center = center.plus(Offset(dotOffset,0f))
                    )

                    drawCircle(
                        color = diceDotsColor,
                        radius = dotRadius,
                        center = center.minus(Offset(dotOffset,0f))
                    )
                }
            }

            val textTopPadding = (fillFraction * 64).dp
            Text(text = stringResource(id = R.string.tap_to_generate_new_number),
                fontWeight = FontWeight.Thin,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = textTopPadding)
            )
        }
        else{
            Text(
                "?",
                fontSize = 96.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}