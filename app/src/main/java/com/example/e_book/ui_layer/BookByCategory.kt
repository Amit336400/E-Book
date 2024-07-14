package com.example.e_book.ui_layer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun BookByCategory(category: String, navController: NavHostController) {


    Column {
        Text(text = category)
    }
}