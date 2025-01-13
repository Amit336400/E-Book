package com.example.e_book.ui_layer.navigation

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

sealed class NavigationItem {

    @Serializable
    object HomeScreen

    @Serializable
    data class BookByCategory(val category :String)

    @Serializable
    data class PdfShowScreen(val PdfUrl :String)

    @Serializable
    object addBokRout


}