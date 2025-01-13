package com.example.e_book.ui_layer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.e_book.ui_layer.navigation.NavigationItem
import com.example.e_book.ui_layer.viewmodel.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Book(modifier: Modifier,viewModel: viewModel = hiltViewModel(),navController: NavController) {
    LaunchedEffect(key1 = true) {
        viewModel.getBooksData()
    }

       val res=viewModel.stste.value
        if (res.isLoading){
            Column(
                modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        if (res.error.isNotEmpty()){
            Column(
                modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${res.error}")
            }
        }
        if (res.items.isNotEmpty()){
            Box (
                modifier
                    .fillMaxSize()
                    .padding(10.dp)){
               LazyColumn {
                   items(res.items){
                       Card(
                           onClick = {
                               navController.navigate(NavigationItem.PdfShowScreen(PdfUrl = it.bookUrl))
                           },
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(bottom = 10.dp)
                       ) {
                           Column(
                               modifier.padding(top = 30.dp, bottom = 30.dp, start = 10.dp)
                                   .fillMaxWidth(),
                               horizontalAlignment = Alignment.CenterHorizontally
                           ) {

                               AsyncImage(
                                   modifier = Modifier
                                       .height(300.dp)
                                       .fillMaxWidth(.6f),
                                   model = it.BoolPhoto, contentDescription = null
                               )
                               Text(text = it.bookName)

                           }
                           Spacer(modifier = modifier.height(10.dp))
                       }
                   }
               }
            }
        }

    }



