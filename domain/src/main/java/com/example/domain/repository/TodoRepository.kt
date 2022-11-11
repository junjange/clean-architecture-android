package com.example.domain.repository

import com.example.domain.BaseResult
import com.example.domain.model.Todo

interface TodoRepository {
    suspend fun createTodo(todo: Todo): BaseResult<Unit>

    suspend fun getTodoList(): BaseResult<List<Todo>>

    suspend fun searchPost(query: String) : BaseResult<List<Todo>>

    suspend fun getTodo(todoIdx: Int): BaseResult<Todo>

    suspend fun deleteTodo(todoIdx: Int): BaseResult<Unit>

    suspend fun deleteTodoList(): BaseResult<Unit>

    suspend fun updatePostLocal(todoIdx: Int, title: String?, body: String?): BaseResult<Unit>

    suspend fun isCheckTodo(todoIdx: Int): BaseResult<Unit>
}