package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Android 클래스에 종속 항목 삽입
 * Application 클래스에 Hilt를 설정하고 애플리케이션 수준 구성요소를 사용할 수 있게 되면
 * Hilt는 @AndroidEntryPoint 주석이 있는 다른 Android 클래스에 종속 항목을 제공할 수 있다.
 *
 * Hilt 결합 정의
 * 필드 삽입을 실행하려면 Hilt가 해당 구성요소에서 필요한 종속 항목의 인스턴스를 제공하는 방법을 알아야 한다.
 * 결합에는 특정 유형의 인스턴스를 종속 항목으로 제공하는 데 필요한 정보가 포함된다.
 * Hilt에 결합 정보를 제공하는 한 가지 방법은 생성자 삽입이다.
 *
 *
 * NavHostActivity를 통해 각 Fragment를 연결
 *
 * */

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navhost)
        initController()
    }

    private fun initController() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}