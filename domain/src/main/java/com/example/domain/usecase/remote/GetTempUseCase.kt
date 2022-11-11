package com.example.domain.usecase.remote

import com.example.domain.BaseResult
import com.example.domain.model.Reserves
import com.example.domain.repository.TempRepository
import javax.inject.Inject

class GetTempUseCase @Inject constructor(
    private val repository: TempRepository
) {
    suspend operator fun invoke(): BaseResult<Reserves> = repository.getTempList()
}