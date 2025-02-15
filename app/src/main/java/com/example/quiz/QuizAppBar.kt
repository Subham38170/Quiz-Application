package com.example.quiz

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun QuizAppBar(
        QuizCategory: String,
        onBackClick: () -> Unit
    ){
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = colorResource(id = R.color.dark_slate_blue),
                actionIconContentColor = colorResource(id = R.color.blue_grey),
                navigationIconContentColor = colorResource(id = R.color.blue_grey)
            ),
            title = {
                Text(
                    text = QuizCategory,
                    color = colorResource(id = R.color.blue_grey),
                    overflow = TextOverflow.Ellipsis
                ) },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_keyboard_backspace), contentDescription = null)
                }
            }
        )
}
