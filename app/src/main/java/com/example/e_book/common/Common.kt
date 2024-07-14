package com.example.e_book.common


sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val error: Throwable) : ResultState<T>()
    object Loading : ResultState<Nothing>()
}

data class BookModel(
    val bookUrl: String= "",
    val category :String ="",
    val bookName: String= ""
)
data class  BookCategoryModel(
    val category : String = ""
)