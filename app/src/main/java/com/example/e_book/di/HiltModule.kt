package com.example.e_book.di

import com.example.e_book.data_layer.network.RepoImpl.AllBookRepoImpl1
import com.example.e_book.domain_layer.Repo
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Singleton
    @Provides
    fun provideFirebaseRealtimeDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()

    }

    @Singleton
    @Provides
    fun provideRepo(firebaseDatabase: FirebaseDatabase) : Repo{
        return AllBookRepoImpl1(firebaseDatabase)
    }
}