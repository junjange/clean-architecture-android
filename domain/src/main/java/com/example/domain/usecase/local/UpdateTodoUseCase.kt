package com.example.domain.usecase.local

import com.example.domain.BaseResult
import com.example.domain.repository.TodoRepository
import javax.inject.Inject

class UpdateTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(
        todoIdx: Int,
        title: String?,
        body: String?
    ): BaseResult<Unit> = repository.updatePostLocal(todoIdx, title, body)
}