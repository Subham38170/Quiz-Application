package com.example.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimensions.HomeTopBoxHeight)
            .background(
                color = colorResource(id = R.color.dark_slate_blue),
                shape = RoundedCornerShape(
                    bottomStart = Dimensions.ExtraLargeCornerRadius,
                    bottomEnd = Dimensions.ExtraLargeCornerRadius
                )
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimensions.LargePadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_open),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimensions.MediumIconSize),
                tint = colorResource(id = R.color.blue_grey)
            )
            Text(
                text = "Quiz Quest",
                color = colorResource(id = R.color.blue_grey),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(3.5f),
                textAlign = TextAlign.Center,
                fontSize = Dimensions.LargeTextSize
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_account_box),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimensions.MediumIconSize),
                tint = colorResource(id = R.color.blue_grey)
            )
        }
    }
}