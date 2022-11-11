package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.TodoDao
import com.example.data.model.local.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 6,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}