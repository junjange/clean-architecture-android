package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.local.TodoEntity

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTodo(todo: TodoEntity)

    @Query("SELECT * FROM todo ORDER BY todoIdx DESC")
    suspend fun getTodoList(): List<TodoEntity>

    @Query("SELECT * FROM todo WHERE todoIdx = :idx ")
    suspend fun getTodo(idx: Int): TodoEntity

    @Query("DELETE FROM todo WHERE todoIdx = :idx")
    suspend fun deleteTodo(idx: Int)

    @Query("DELETE FROM todo")
    suspend fun deleteTodoList()

    @Query("UPDATE todo SET title = :title, body = :body WHERE todoIdx = :id")
    suspend fun patchTodo(id: Int, title: String?, body: String?)

    @Query("SELECT DISTINCT * FROM todo WHERE title LIKE '%'||:query||'%' OR body LIKE '%'||:query||'%' ORDER BY todoIdx DESC")
    suspend fun searchTodo(query: String): List<TodoEntity>

    @Query("UPDATE todo SET isChecked = not isChecked  WHERE todoIdx = :id")
    suspend fun isCheckTodo(id: Int)
}