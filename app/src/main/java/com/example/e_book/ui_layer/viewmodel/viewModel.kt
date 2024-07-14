package com.example.e_book.ui_layer.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.e_book.common.BookCategoryModel
import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState

import com.example.e_book.domain_layer.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewModel @Inject constructor(val repo: Repo) : ViewModel() {
    private val _state: MutableState<itemState> = mutableStateOf(itemState())
    val stste: MutableState<itemState> = _state

    init {
        getBookCategory()
    }

   fun getBooksData(){
       viewModelScope.launch {
           getData()
       }
   }
    suspend fun getData(){
        repo.getAllBook().collect {
            when (it) {
                is ResultState.Error -> {
                    _state.value = itemState(error = it.error.localizedMessage)
                }

                ResultState.Loading -> {
                    _state.value = itemState(isLoading = true)
                }

                is ResultState.Success -> {
                    _state.value = itemState(items = it.data)

                }
            }
        }
    }


    fun getBookCategory(){
        viewModelScope.launch {
            getBookCategory1()
        }

    }
    suspend fun getBookCategory1(){
        repo.getAllBookCategory().collect{
            when(it){
                is ResultState.Error -> {
                    _state.value=itemState(error = it.error.localizedMessage)
                }
                ResultState.Loading -> {
                    _state.value=itemState(isLoading = true)

                }
                is ResultState.Success -> {
                    _state.value=itemState(category = it.data)

                }
            }
        }

    }


}

data class itemState(
    val items: List<BookModel> = emptyList(),
    val error: String = "",
    val category : List<BookCategoryModel> = emptyList(),
    val isLoading: Boolean = false,
)