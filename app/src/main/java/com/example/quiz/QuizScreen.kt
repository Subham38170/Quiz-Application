package com.example.quiz


import android.text.Html
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.plus
sealed class ScreenState {
    object Loading : ScreenState()
    object Success : ScreenState()
    object Error : ScreenState()
}
data class QuizInfo(
    val question: String,
    val correct_answer: String,
    val incorrect_ans: List<String>,
    var shuffled_options: List<String> =  (incorrect_ans+correct_answer).shuffled()
)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    numOfQuiz: String,
    QuizCategory: String,
    QuizDifficulty: String,
    quizType: String,
    navigateToScoreScreen: (Int,Int) -> Unit,
    navigateToMainScreen: ()->Unit

) {
    val context = LocalContext.current
    var screenState by remember { mutableStateOf<ScreenState>(ScreenState.Loading) }
    var quizList by remember { mutableStateOf<List<Quiz>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var correct by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        val difficulty = when (QuizDifficulty) {
            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }
        val type = when (quizType) {
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }
        withContext(Dispatchers.IO) {
            try {
                val response = triviaApiService
                    .getQuizzes(
                        numOfQuiz.toInt(),
                        constants.categoriesMap.get(QuizCategory)!!,
                        difficulty,
                        type
                    ).execute()
                if (response.isSuccessful) {
                    val quizResponse = response.body()
                    if (quizResponse != null) {
                        quizList = quizResponse.results
                        screenState = ScreenState.Success
                    } else {
                        errorMessage = "Response body is null"
                        screenState = ScreenState.Error
                    }
                }
            } catch (e: Exception) {
                errorMessage = "Network Error: ${e.message}"
                screenState = ScreenState.Error
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        QuizAppBar(QuizCategory = QuizCategory) { navigateToMainScreen() }
        Column(
            modifier = Modifier
                .padding(Dimensions.VerySmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Questions : $numOfQuiz",
                    color = colorResource(id = R.color.blue_grey)
                )
                Text(
                    text = QuizDifficulty,
                    color = colorResource(id = R.color.blue_grey)
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimensions.MediumRoundedCornerShape)),
                thickness = Dimensions.VerySmallPadding,
                color = colorResource(id = R.color.blue_grey)
            )
            Spacer(modifier = Modifier.height(Dimensions.LargeSpacerHeight))
            when (screenState) {
                ScreenState.Loading -> {
                    ShimmerEffectQuizInterface()
                    BackHandler {
                        navigateToMainScreen()
                    }
                }
                ScreenState.Error -> {
                    LaunchedEffect(errorMessage) {
                        Toast.makeText(context, errorMessage ?: "An unknown error occurred", Toast.LENGTH_SHORT).show()
                        navigateToMainScreen()
                    }
                }
                ScreenState.Success -> {
                    BackHandler { navigateToMainScreen()}
//                    val ansList = remember { mutableStateListOf<String>().apply { repeat(numOfQuiz.toInt()) { add("") } } }
                    val ansList = remember { List(numOfQuiz.toInt()) { mutableStateOf("") } }
                    val pagerState = rememberPagerState { quizList.size }
                    HorizontalPager(state = pagerState) { index ->
                        val currentQuiz = quizList[index]
                        val question = Html.fromHtml(currentQuiz.question, Html.FROM_HTML_MODE_LEGACY).toString()
                        val correct_ans = Html.fromHtml(currentQuiz.correct_answer, Html.FROM_HTML_MODE_LEGACY).toString()
//                        val correct_ans = currentQuiz.correct_answer.replace("&quot;","\"").replace("&#039;","\'")
                        val incorrect_ans =currentQuiz.incorrect_answers.map { Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY).toString() }
//                        val shuffledOptions = remember { (incorrect_ans + correct_ans).shuffled() }
                        val shuffledOptions = remember(incorrect_ans, correct_ans) { (incorrect_ans + correct_ans).shuffled() }
                        QuizInterface(
                            modifier = Modifier.weight(1f),
                            onOptionSelected = {text->
                                if(ansList[index].value.isNotEmpty() && ansList[index].value==correct_ans) correct--
                                else if(text==correct_ans) correct++
                                ansList[index].value=text
                            },
                            qNumber = index+1,
                            quiz = QuizInfo(
                                question = question,
                                correct_answer = correct_ans,
                                incorrect_ans = incorrect_ans,
                                shuffled_options = shuffledOptions
                            ),
                            selectedOption = ansList[index].value,
                            onUnselectedOption = {
                                if(ansList[index].value==correct_ans) correct--
                                ansList[index].value = ""
                            }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                    ) {
                        val scope = rememberCoroutineScope()
                        ButtonBox(
                            text = if (pagerState.currentPage != 0) "Previous" else "",
                            padding = Dimensions.SmallPadding,
                            fraction = 0.43f,
                            fontSize = Dimensions.SmallTextSize
                        ) {
                            if (pagerState.currentPage != 0)
                                scope.launch { pagerState.animateScrollToPage(page = pagerState.currentPage - 1) }
                        }
                        ButtonBox(
                            text = if (pagerState.currentPage == quizList.size - 1) "Submit" else "Next",
                            padding = Dimensions.SmallPadding,
                            borderColor = colorResource(id = R.color.orange),
                            containerColor = colorResource(id = R.color.dark_slate_blue),
                            fraction = 1f,
                            textColor = colorResource(id = R.color.white),
                            fontSize = Dimensions.SmallTextSize
                        ) {
                            if (pagerState.currentPage != quizList.size - 1) {
                                scope.launch { pagerState.animateScrollToPage(page = pagerState.currentPage + 1) }
                            } else {
                                navigateToScoreScreen(numOfQuiz.toInt(),correct)
                            }
                        }
                    }
                }
            }
        }
    }
}




