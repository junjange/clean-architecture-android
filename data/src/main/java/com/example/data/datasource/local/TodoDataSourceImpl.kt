package com.example.data.datasource.local

import com.example.data.dao.TodoDao
import com.example.data.handleResult
import com.example.data.mapper.toDomain
import com.example.data.model.local.TodoEntity
import com.example.domain.BaseResult
import com.example.domain.model.Todo
import java.lang.Exception
import javax.inject.Inject

class TodoDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
    ) : TodoDataSource {

    override suspend fun createTodo(todo: TodoEntity): BaseResult<Unit> {
        return handleResult { todoDao.createTodo(todo) }
    }

    override suspend fun getTodoList(): BaseResult<List<Todo>> {
        return handleResult { todoDao.getTodoList().toDomain() }
    }

    override suspend fun getTodo(todoIdx: Int): BaseResult<Todo> {
        return handleResult { todoDao.getTodo(todoIdx).toDomain() }
    }

    override suspend fun deleteTodo(todoIdx: Int): BaseResult<Unit> {
        return handleResult { todoDao.deleteTodo(todoIdx) }
    }

    override suspend fun deleteTodoList(): BaseResult<Unit> {
        return handleResult { todoDao.deleteTodoList() }
    }

    override suspend fun updatePostLocal(todoIdx: Int, title: String?, body: String?): BaseResult<Unit> {
        return handleResult {
            val todoList = (getTodoList() as BaseResult.Success).data
            if(todoList.filter { todo -> todo.todoIdx != todoIdx }.any{todo -> todo.title == title && todo.body == body}){
                throw Exception()
            }else{
                todoDao.patchTodo(todoIdx,title,body)
            }
        }
    }

    override suspend fun searchTodo(query: String): BaseResult<List<Todo>> {
        return handleResult { todoDao.searchTodo(query).toDomain() }
    }

    override suspend fun isCheckTodo(todoIdx: Int): BaseResult<Unit> {
        return handleResult {
            todoDao.isCheckTodo(todoIdx)
        }
    }
}