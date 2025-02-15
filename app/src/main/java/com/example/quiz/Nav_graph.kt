package com.example.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument




const val mainScreen= "main_screen"
fun quizScreen(
    numOfQuiz: String = "{numOfQuiz}",
    category: String = "{category}",
    difficulty: String = "{difficulty}",
    type: String = "{type}"
): String = "second_screen/$numOfQuiz/$category/$difficulty/$type"
fun scoreScreen(numOfQuiz: String="{numOfQuiz}",correct: String="{correct}"): String = "score_screen/$numOfQuiz/$correct"

@Composable
fun NavGraph(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = mainScreen
    ){
        composable(route = mainScreen){
            HomeScreen { numOfQuiz,category,difficulty,type->
                navController.navigate(route = quizScreen(numOfQuiz,category,difficulty,type))
            }
        }
        composable(
            route = quizScreen(),
            arguments = listOf(
                navArgument("numOfQuiz"){type = NavType.StringType},
                navArgument("category"){type = NavType.StringType},
                navArgument("difficulty"){type = NavType.StringType},
                navArgument("type"){type = NavType.StringType}
            )
        ){
            val numOfQuiz = it.arguments?.getString("numOfQuiz")!!
            val category = it.arguments?.getString("category")!!
            val difficulty = it.arguments?.getString("difficulty")!!
            val type = it.arguments?.getString("type")!!
            QuizScreen(
                numOfQuiz = numOfQuiz,
                QuizCategory = category,
                QuizDifficulty = difficulty,
                quizType = type,
                navigateToScoreScreen = {no,correct -> navController.navigate( scoreScreen(no.toString(),
                    correct.toString()
                ))},
                navigateToMainScreen = {navController.popBackStack()}
            )
        }
        composable(
            route = scoreScreen(),
            arguments = listOf(
                navArgument("numOfQuiz"){type = NavType.StringType},
                navArgument("correct"){type = NavType.StringType}
            )
            ){
            val numOfQuiz = it.arguments?.getString("numOfQuiz")?.toInt()
            val correct = it.arguments?.getString("correct")?.toInt()
            finalScoreScreen(numOfQuiz!!,correct!!){
                navController.navigate(route = mainScreen){
                    popUpTo(mainScreen){inclusive = true}
                }
            }
        }
    }
}


