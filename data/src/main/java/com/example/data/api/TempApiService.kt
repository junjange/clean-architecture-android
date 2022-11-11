package com.example.data.api

import com.example.data.model.remote.*
import com.google.gson.JsonObject
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TempApiService {

    // 경기 세부 정보 제공
    @Headers("Content-Type: application/json")
    @GET("reserves/{reserveId}/reserve-info")
    suspend fun getReservesInfo(
        @Path("reserveId") reserveId : Int,

        ): ReservesInfoResponse

}