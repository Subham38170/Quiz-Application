package com.example.quiz

import android.icu.text.DecimalFormat
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.LottieAnimation

@Composable
fun finalScoreScreen(
    numOfQuestions: Int,
    numOfCorrectAns: Int,
    navigateToHomeScreen : ()-> Unit
){
    BackHandler {navigateToHomeScreen()}
    val composition1 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congratulation))
    val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congra))


    val annonatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Black)){append("You attempted ")}
        withStyle(style = SpanStyle(color = Color.Blue)){append("$numOfQuestions questions")}
        withStyle(style = SpanStyle(color = Color.Black)){append(" and from that ")}
        withStyle(style = SpanStyle(color = colorResource(id = R.color.green))){append("$numOfCorrectAns answers")}
        withStyle(style = SpanStyle(color = Color.Black)){append(" are correct")}
    }
    val scorePercentage = calculatePercentage(numOfCorrectAns,numOfQuestions)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimensions.MediumPadding),
        verticalArrangement = Arrangement.Center
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(
                onClick = { navigateToHomeScreen() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_close),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimensions.MediumCornerRadius))
                .background(colorResource(id = R.color.blue_grey)),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimensions.MediumPadding,
                    vertical = Dimensions.MediumPadding
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box {
                    LottieAnimation(
                        modifier = Modifier.size(Dimensions.LargeLottAnimSize),
                        composition = composition1,
                        iterations = 100
                    )
                    LottieAnimation(
                        modifier = Modifier.size(Dimensions.LargeLottAnimSize),
                        composition = composition2,
                        iterations = 100
                    )
                }
                Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
                Text(
                    text = "Congrats!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimensions.MediumTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimensions.MediumSpacerHeight))
                Text(
                    text = "$scorePercentage% Score",
                    color = colorResource(id = R.color.green),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimensions.MediumTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimensions.MediumSpacerHeight))
                Text(
                    text = annonatedString,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimensions.SmallTextSize,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimensions.LargeSpacerHeight))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Share With us : ",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = Dimensions.SmallTextSize
                    )
                    Spacer(modifier = Modifier.width(Dimensions.SmallSpacerWidth))
                    icon(painter = painterResource(id = R.drawable.instagram))
                    Spacer(modifier = Modifier.width(Dimensions.SmallSpacerWidth))
                    icon(painter = painterResource(id = R.drawable.facebook))
                    Spacer(modifier = Modifier.width(Dimensions.SmallSpacerWidth))
                    icon(painter = painterResource(id = R.drawable.whtsp))

                }

            }
        }
    }
}
fun calculatePercentage(a: Int,n: Int): Double{
    require(a>=0 && n>0){"In valid input: a must be non-negative and n must be positive"}
    val percentage = (a.toDouble()/n.toDouble())*100.0
    return DecimalFormat("#.##").format(percentage).toDouble()
}
@Composable
fun icon(
    painter: Painter
){
    Icon(
        painter = painter,
        modifier = Modifier.size(28.dp).clip(shape = CircleShape),
        contentDescription = "",
        tint = Color.Unspecified
    )
}