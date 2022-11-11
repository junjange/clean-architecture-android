package com.example.data.datasource.local

import com.example.data.model.local.TodoEntity
import com.example.domain.BaseResult
import com.example.domain.model.Todo

interface TodoDataSource {
    suspend fun createTodo(todo: TodoEntity): BaseResult<Unit>

    suspend fun getTodoList(): BaseResult<List<Todo>>

    suspend fun getTodo(todoIdx: Int): BaseResult<Todo>

    suspend fun deleteTodo(todoIdx: Int): BaseResult<Unit>

    suspend fun deleteTodoList(): BaseResult<Unit>

    suspend fun updatePostLocal(todoIdx: Int, title: String?, body: String?): BaseResult<Unit>

    suspend fun searchTodo(query: String) : BaseResult<List<Todo>>

    suspend fun isCheckTodo(todoIdx: Int): BaseResult<Unit>
}
