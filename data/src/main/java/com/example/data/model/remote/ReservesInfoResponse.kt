package com.example.data.model.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 *  @field:Json 과 @SerializedName 차이가 있는건지.. 평소 하는 방식대로 직렬화 후 API를 불러야만 실행된다.
 *  알아보니  @field:Json으로 불러오는 API와 내가 평소에 사용했던 API 구조가 다른듯?
 *
 *
 * */
@JsonClass(generateAdapter = true)
data class ReservesInfoResponse(@SerializedName("data") val reservesInfoResponseData: ReservesInfoResponseData)


@JsonClass(generateAdapter = true)
data class ReservesInfoResponseData(
    @field:Json(name = "reserveId") val reserveId: Int,
    @field:Json(name = "sport") val sport: String,
    @field:Json(name = "reserveDate") val reserveDate: String,
    @field:Json(name = "startT") val startT: String,
    @field:Json(name = "endT") val endT: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "explanation") val explanation: String,
    @field:Json(name = "currentNum") val currentNum: Int,
    @field:Json(name = "recruitmentNum") val recruitmentNum: Int,
    @field:Json(name = "place") val place: String ,
    @field:Json(name = "gender") val gender: String ,
    @field:Json(name = "reserveStatus") val reserveStatus: String,
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "schoolNum") val schoolNum: String,
    @field:Json(name = "name") val name: String
)
