package com.example.quiz

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShimmerEffectQuizInterface(

) {

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .height(120.dp)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(120.dp)
                    .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.LargeSpacerHeight))
        Box(
            modifier = Modifier
                .padding(horizontal = Dimensions.SmallPadding)
                .fillMaxWidth()
                .height(Dimensions.MediumBoxHeight)
                .clip(RoundedCornerShape(Dimensions.LargeCornerRadius))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))

        Box(
            modifier = Modifier
                .padding(horizontal = Dimensions.SmallPadding)
                .fillMaxWidth()
                .height(Dimensions.MediumBoxHeight)
                .clip(RoundedCornerShape(Dimensions.LargeCornerRadius))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
        Box(
            modifier = Modifier
                .padding(horizontal = Dimensions.SmallPadding)
                .fillMaxWidth()
                .height(Dimensions.MediumBoxHeight)
                .clip(RoundedCornerShape(Dimensions.LargeCornerRadius))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
        Box(
            modifier = Modifier
                .padding(horizontal = Dimensions.SmallPadding)
                .fillMaxWidth()
                .height(Dimensions.MediumBoxHeight)
                .clip(RoundedCornerShape(Dimensions.LargeCornerRadius))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
        Spacer(modifier = Modifier.height(Dimensions.LargeSpacerHeight))

        Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimensions.MediumPadding)
            .navigationBarsPadding()
        ){
            Box(
                modifier = Modifier
                    .padding(Dimensions.SmallPadding)
                    .weight(4f)
                    .height(Dimensions.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimensions.LargeCornerRadius))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .padding(Dimensions.SmallPadding)
                    .weight(4f)
                    .height(Dimensions.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimensions.LargeCornerRadius))
                    .shimmerEffect()
            )
        }
    }
}

fun Modifier.shimmerEffect() = composed{
    val transition = rememberInfiniteTransition(label = " ")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).value
    background(colorResource(id = R.color.blue_grey).copy(alpha = alpha))
}
