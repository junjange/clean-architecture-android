package com.example.data.di

import com.example.data.datasource.local.TodoDataSource
import com.example.data.repository.TodoRepositoryImpl
import com.example.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTodoRepository(
        todoDataSource: TodoDataSource
    ): TodoRepository {
        return TodoRepositoryImpl(todoDataSource)
    }

}