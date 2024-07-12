package com.example.e_book.data_layer.network.Repo

import com.example.e_book.common.BookModel
import com.example.e_book.common.ResultState
import com.example.e_book.domain_layer.AllBookRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepoImpl1 @Inject constructor(val firebaseDatabase: FirebaseDatabase) : AllBookRepo {
    override fun getAllBook(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var items: List<BookModel> = emptyList()
                items = snapshot.children.map {
                    it.getValue(BookModel::class.java)!!
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


}