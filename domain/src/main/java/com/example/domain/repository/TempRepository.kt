package com.example.domain.repository

import com.example.domain.BaseResult
import com.example.domain.model.Reserves

interface TempRepository {
    suspend fun getTempList(): BaseResult<Reserves>

}