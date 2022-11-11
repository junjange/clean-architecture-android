package com.example.data.di


import com.example.data.api.ApiClient.BASE_URL
import com.example.data.api.TempApiService
import com.example.data.datasource.remote.*
import com.example.data.repository.TempRepositoryImpl
import com.example.domain.repository.TempRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TempModule {

private var token: String = ""

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor {

            // Request
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", token)
                .build()

            // Response
            val response = it.proceed(request)
            response
        }.build()


    @Singleton
    @Provides
    fun provideTempApi(
    ): TempApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()))
        .build().create(TempApiService::class.java)


    @Provides
    @Singleton
    fun provideTempDataSource(
        tempApiService: TempApiService,
    ): TempDataSource {
        return TempDataSourceImpl(tempApiService)
    }

    @Provides
    @Singleton
    fun provideTempRepository(
        dataSource: TempDataSource
    ): TempRepository {
        return TempRepositoryImpl(dataSource)
    }

}