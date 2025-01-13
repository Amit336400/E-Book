package com.example.e_book.ui_layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.e_book.R
import com.rizzi.bouquet.dp


@Preview(showSystemUi = true)
@Composable
fun BoolCard() {
    Card(
        onClick = { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        modifier = Modifier.padding(10.dp()).fillMaxSize()
    ) {
        Column {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = R.drawable.ic_launcher_background,
                contentDescription = null
            )
            Text(text = "Amit K")
        }

    }

}