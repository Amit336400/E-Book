package com.example.e_book.data_layer.network.RepoImpl


import com.example.e_book.common.BookCategoryModel
import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState
import com.example.e_book.domain_layer.Repo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepoImpl1 @Inject constructor(val firebaseDatabase: FirebaseDatabase) : Repo {
    override fun getAllBook(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {



                var items: List<BookModel> = emptyList()
                items = snapshot.children.map {value->

                    value.getValue<BookModel>()!!
                }
                trySend(ResultState.Success(items))


            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }

        }
        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)

        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
        }
    }

    override fun getAllBookCategory(): Flow<ResultState<List<BookCategoryModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items :List<BookCategoryModel> = emptyList()
                items = snapshot.children.map {
                    it.getValue<BookCategoryModel>()!!
                }
                trySend(ResultState.Success(data = items))
            }

            override fun onCancelled(error: DatabaseError) {
               trySend(ResultState.Error(error = error.toException()))
            }
        }
        firebaseDatabase.reference.child("BookCategory").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.child("BookCategory").removeEventListener(valueEvent)
            close()
        }
    }

    override fun getBookByCategory(category: String): Flow<ResultState<List<BookModel>>> =
        callbackFlow {
            trySend(ResultState.Loading)

            val valueEvent = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var items: List<BookModel> = emptyList()
                    items = snapshot.children.filter {
                        it.getValue<BookModel>()!! .category==category
                    }.map {
                        it.getValue<BookModel>()!!
                    }
                    trySend(ResultState.Success(data = items))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(ResultState.Error(error = error.toException()))
                }
            }
            firebaseDatabase.reference.child("Books")
                .addValueEventListener(valueEvent)

            awaitClose{
                firebaseDatabase.reference.child("Books").removeEventListener(valueEvent)
                close()
            }

        }
}