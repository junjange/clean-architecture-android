package com.example.domain.usecase.local

import com.example.domain.BaseResult
import com.example.domain.model.Todo
import com.example.domain.repository.TodoRepository
import javax.inject.Inject

class SearchTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(query: String): BaseResult<List<Todo>> = repository.searchPost(query)
}