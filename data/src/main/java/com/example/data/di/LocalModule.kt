package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.local.TodoDataSource
import com.example.data.datasource.local.TodoDataSourceImpl
import com.example.data.db.TodoDatabase
import com.example.data.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideTodoDataSource(todoDao: TodoDao): TodoDataSource {
        return TodoDataSourceImpl(todoDao)
    }

    //room
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java, "Aos_Onboard.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao()
    }

}