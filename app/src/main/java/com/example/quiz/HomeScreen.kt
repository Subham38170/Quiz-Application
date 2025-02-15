package com.example.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quiz.Dimensions.MediumPadding
import com.example.quiz.Dimensions.MediumSpacerHeight
import com.example.quiz.Dimensions.SmallSpacerHeight
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun HomeScreen(
    navigateToQuizScreen: (String, String, String,String) ->Unit
) {
    var numberOfQuiz by remember { mutableStateOf(constants.numberAsString[0]) }
    var category by remember { mutableStateOf(constants.categories[0]) }
    var difficulty by remember { mutableStateOf(constants.difficulty[0]) }
    var type by remember { mutableStateOf(constants.type[0]) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = colorResource(id = R.color.mid_night_blue))
    ) {
        HomeHeader()

        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        AppDropDownMenu(
            menuName = "Number of Questions",
            menuList = constants.numberAsString){numberOfQuiz = it}

        Spacer(modifier = Modifier.height(SmallSpacerHeight))

        AppDropDownMenu(
            menuName = "Select Category",
            menuList = constants.categories,
        ){category = it}

        Spacer(modifier = Modifier.height(SmallSpacerHeight))

        AppDropDownMenu(
            menuName = "Select Difficulty",
            menuList = constants.difficulty,
        ){difficulty = it}

        Spacer(modifier = Modifier.height(SmallSpacerHeight))

        AppDropDownMenu(
            menuName = "Select Type",
            menuList = constants.type
        ){type = it}

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        ButtonBox(
            text = "Generate Quiz",
            padding = MediumPadding,
            onButtonClick = { navigateToQuizScreen(numberOfQuiz,category,difficulty,type) }
        )
    }
}