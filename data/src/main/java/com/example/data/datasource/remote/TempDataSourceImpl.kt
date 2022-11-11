package com.example.data.datasource.remote


import android.util.Log
import com.example.data.api.TempApiService
import com.example.data.handleResult
import com.example.data.mapper.toDomain
import com.example.domain.BaseResult
import com.example.domain.model.Reserves
import javax.inject.Inject

class TempDataSourceImpl @Inject constructor(
    private val tempApiService: TempApiService,

    ) : TempDataSource {

    override suspend fun retrofitReservesInfo(): BaseResult<Reserves> {
        Log.d("Ttt", handleResult{tempApiService.getReservesInfo(reserveId = 16).reservesInfoResponseData.toDomain()}.toString())
        return handleResult{tempApiService.getReservesInfo(reserveId = 16).reservesInfoResponseData.toDomain()}
    }

}