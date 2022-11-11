package com.example.myapplication.common.di

import android.app.Application
import com.example.myapplication.common.base.DataStoreModule
import dagger.hilt.android.HiltAndroidApp


/**
 * Hilt 애플리케이션 클래스
 * Hilt를 사용하는 모든 앱은 @HiltAndroidApp으로 주석이 지정된 Application 클래스를 포함해야 한다.
 * @HiltAndroidApp은 애플리케이션 수준 종속 항목 컨테이너 역할을 하는 애플리케이션의 기본 클래스를 비롯하여 Hilt의 코드 생성을 트리거한다.
 *
 * */
@HiltAndroidApp
class MyApplication : Application(){

    private lateinit var dataStore : DataStoreModule

    companion object {
        private lateinit var sampleApplication: MyApplication
        fun getInstance() : MyApplication = sampleApplication
    }

    override fun onCreate() {
        super.onCreate()
        sampleApplication = this
        dataStore = DataStoreModule(this)
    }

    fun getDataStore() : DataStoreModule = dataStore
}