package com.example.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Composable
fun QuizInterface(
    onOptionSelected: (String)-> Unit,
    qNumber: Int,
    modifier: Modifier = Modifier,
    quiz: QuizInfo,
    onUnselectedOption: ()-> Unit,
    selectedOption: String
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.wrapContentHeight()
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "$qNumber.",
                    modifier = Modifier.weight(1f),
                    color = colorResource(id = R.color.blue_grey),
                    fontSize = Dimensions.SmallTextSize
                )
                Text(
                    text = quiz.question,
                    color = colorResource(id = R.color.blue_grey),
                    fontSize = Dimensions.MediumTextSize,
                    modifier = Modifier.weight(9f),
                    maxLines = 4,
                    minLines = 2,
                    lineHeight = 25.sp
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.LargeSpacerHeight))
            Column (
                modifier = Modifier.padding(horizontal = 15.dp)
            ){

                var selOption by remember { mutableStateOf(selectedOption) }
                val optionNumber = listOf("A","B","C","d")
                Column {
                    quiz.shuffled_options.forEachIndexed {index,text->
                            QuizOption(
                                optionNumber = optionNumber[index],
                                options = text.toString(),
                                selected = text==selOption,
                                onOptionClick = {
                                    onOptionSelected(text)
                                    selOption = text
                                },
                                onUnselected = {
                                    selOption=""
                                    onUnselectedOption()
                                }
                            )

                        Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
                    }
                }
                Spacer(modifier = Modifier.height(Dimensions.ExtraLargeSpacerHeight))
            }

        }
    }
}