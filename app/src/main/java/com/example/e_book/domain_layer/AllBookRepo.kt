package com.example.e_book.domain_layer

import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AllBookRepo {
    fun getAllBook(): Flow<ResultState<List<BookModel>>>
}