package com.example.data.datasource.remote

import com.example.domain.BaseResult
import com.example.domain.model.Reserves

interface TempDataSource {

    suspend fun retrofitReservesInfo() : BaseResult<Reserves>
}