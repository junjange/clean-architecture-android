package com.example.data.repository

import com.example.data.datasource.local.TodoDataSource
import com.example.data.mapper.toData
import com.example.domain.BaseResult
import com.example.domain.model.Todo
import com.example.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDataSource: TodoDataSource
) : TodoRepository {

    override suspend fun createTodo(todo: Todo): BaseResult<Unit> {
        return todoDataSource.createTodo(todo.toData())
    }

    override suspend fun getTodoList(): BaseResult<List<Todo>> {
        return todoDataSource.getTodoList()
    }

    override suspend fun searchPost(query: String): BaseResult<List<Todo>> {
        return todoDataSource.searchTodo(query)
    }

    override suspend fun getTodo(todoIdx: Int): BaseResult<Todo> {
        return todoDataSource.getTodo(todoIdx)
    }


    override suspend fun deleteTodo(todoIdx: Int): BaseResult<Unit> {
        return todoDataSource.deleteTodo(todoIdx)
    }

    override suspend fun deleteTodoList(): BaseResult<Unit> {
        return todoDataSource.deleteTodoList()
    }


    override suspend fun updatePostLocal(todoIdx: Int, title: String?, body: String?): BaseResult<Unit> {
        return todoDataSource.updatePostLocal(todoIdx, title, body)
    }

    override suspend fun isCheckTodo(todoIdx: Int): BaseResult<Unit> {
        return todoDataSource.isCheckTodo(todoIdx)
    }
}