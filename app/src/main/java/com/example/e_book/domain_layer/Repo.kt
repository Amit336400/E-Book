package com.example.e_book.domain_layer


import com.example.e_book.common.BookCategoryModel
import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun getAllBook(): Flow<ResultState<List<BookModel>>>
    fun getAllBookCategory (): Flow<ResultState<List<BookCategoryModel>>>
    fun getBookByCategory(category :String) :Flow<ResultState<List<BookModel>>>
}