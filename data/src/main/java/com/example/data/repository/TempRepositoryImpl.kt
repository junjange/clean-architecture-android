package com.example.data.repository

import com.example.data.datasource.remote.TempDataSource
import com.example.domain.BaseResult
import com.example.domain.model.Reserves
import com.example.domain.repository.TempRepository
import javax.inject.Inject

class TempRepositoryImpl @Inject constructor(
    private val tempDataSource: TempDataSource
) : TempRepository {

    override suspend fun getTempList(): BaseResult<Reserves> {
        return tempDataSource.retrofitReservesInfo()
    }

}