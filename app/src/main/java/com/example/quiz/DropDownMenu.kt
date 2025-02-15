package com.example.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownMenu(
    menuName: String,
    menuList: List<String>,
    onDropDownClick: (String)-> Unit
){
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(menuList[0]) }
    Column(
        modifier = Modifier
            .padding(horizontal = Dimensions.MediumPadding)
            .fillMaxWidth()
    ){
        Text(
            text = menuName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = colorResource(id =R.color.blue_grey),
        )
        Spacer(modifier = Modifier.height(Dimensions.SmallSpacerHeight))
        ExposedDropdownMenuBox(
            expanded = false,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedTextColor = colorResource(id = R.color.blue_grey),
                    focusedTextColor = colorResource(id = R.color.blue_grey),
                    unfocusedTrailingIconColor = colorResource(id = R.color.orange),
                    focusedTrailingIconColor = colorResource(id = R.color.orange),
                    focusedBorderColor = colorResource(id = R.color.dark_slate_blue),
                    unfocusedBorderColor = colorResource(id = R.color.dark_slate_blue),
                    containerColor = colorResource(id = R.color.dark_slate_blue)
                ),
                shape = RoundedCornerShape(Dimensions.MediumRoundedCornerShape)
            )
            DropdownMenu(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.mid_night_blue))
                    .heightIn(max = 300.dp),
                expanded = isExpanded,
                onDismissRequest = {isExpanded=false}
            ){

                    menuList.forEachIndexed{index: Int, text: String ->
                    DropdownMenuItem(
                        text = { Text(text = text, color = colorResource(id = R.color.blue_grey)) },
                        onClick = {
                            isExpanded = false
                            selectedText = menuList[index]
                            onDropDownClick(selectedText)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}
