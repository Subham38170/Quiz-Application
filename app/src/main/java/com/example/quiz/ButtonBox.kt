package com.example.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit


@Composable
fun ButtonBox(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = Dimensions.SmallPadding,
    borderColor: Color = colorResource(id = R.color.blue_grey),
    containerColor: Color = colorResource(id = R.color.blue_grey),
    textColor: Color = colorResource(id = R.color.black),
    fontSize: TextUnit = Dimensions.MediumTextSize,
    fraction: Float = 1f,
    onButtonClick: ()-> Unit
){
    Box(
        modifier = Modifier
            .padding(padding)
            .border(
                width = Dimensions.SmallBorderWidth,
                color = if(text!="")borderColor else Color.Transparent,
                shape = RoundedCornerShape(Dimensions.LargeCornerRadius))
            .clickable{if(text!="")onButtonClick()}
            .fillMaxWidth(fraction)
            .height(Dimensions.MediumBoxHeight)
            .clip(RoundedCornerShape(Dimensions.LargeCornerRadius))
            .background(if(text=="Submit") colorResource(id = R.color.orange)  else if(text.isBlank()) Color.Transparent else containerColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = Dimensions.MediumTextSize,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = textColor
        )
    }
}