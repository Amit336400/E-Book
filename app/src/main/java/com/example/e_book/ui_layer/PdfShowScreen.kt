package com.example.e_book.ui_layer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun PdfShowScreen(PdfUrl :String){

    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(PdfUrl), isZoomEnable = true, isAccessibleEnable = true,
    )

    Column(
       modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    VerticalPDFReader(state = pdfState, modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray))


}
}