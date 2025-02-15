package com.example.quiz

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Response class that wraps the whole API response
data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)
// Data class representing each trivia question
data class Quiz(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
)
interface ApiService {
    @GET("api.php")
    fun getQuizzes(
        @Query("amount") amount: Int = 5, // Default to 5 questions
        @Query("category") category: Int = 9, // Default category: General Knowledge
        @Query("difficulty") difficulty: String = "easy", // Default difficulty: easy
        @Query("type") type: String = "multiple" // Default question type: multiple
    ): Call<QuizResponse>
}
private val retrofit = Retrofit.Builder()
    .baseUrl("https://opentdb.com/")
    .addConverterFactory(GsonConverterFactory.create()) // Gson for JSON conversion
    .build()

// Create the API service interface
val triviaApiService: ApiService = retrofit.create(ApiService::class.java)