package com.example.myapplication.common.base

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication.common.util.isNightMode

/**
 * 공부를 시작하기 전에 BaseActivity와 BaseFragment에 대해서 알아보았다.
 * BaseActivity, BaseFragment 를 통해 보일러 플레이트 코드(반복되는 코드)를 정리하기 위해서!
 * 간단히 말하자면, "코드의 중복을 줄이고 가독성을 높이기 위해"서 프로젝트의 뼈대로 사용되는 클래스이다.
 * 보통 binding이나 그 외 여러 activity/fragment 걸처 공통적으로 수행하는 코드에 대하여 초기화나 이벤트 등을 정리해둠으로서
 * 나중에 다른 activity/fragment에서 이를 상속하여 사용한다.
 *
 * 대신
 * 1. 공통적인 코드라고 무조건 Base에 넣는 것은 좋은 코드라고 할 수 없다. 이럴 경우 차라리 Util 관련 클래스를 만들어 따로 빼서 쓰는 것이 더 이상적일 것
 * 2.  lifecycle을 꼭 지키도록 설계
 * 3. 몇개가 만들어질지 모르는 변수들(ex : viewmodel)은 base 부분에 넣지 않는 것을 추천?
 *
 * BaseActivity란 여러 Activity를 사용할 때 중복되는 코드를 미리 정의하여 소스를 간소화 및 간단하게 만든다.
 * 화장품으로 치면 본격적인 화장에 들어가기 전에 바르는 파운데이션 느낌
 *
 *
 *
 * */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Handle StatusBar Appearance
         * 상태표시줄의 배경색과 내용물을 바꾸는 방법은 매우 간단하다.
         * WindowInsetsController에서는 setSystemBarsAppearance 함수를 제공한다.
         * 방법은 아래와 같이 사용 가능하다.
         *
         * 참고 : https://soda1127.github.io/deep-dive-in-android-full-screen-2/
         * */

        // API 30이상에서만 사용이 가능한 상황이고, Light 모드이냐, Dark 모드이냐와 같이 간단한 조건을 주고 색상을 바꾸도록 한 것이다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            // 기본적으로 제공하는 값은 APPEARANCE_LIGHT_STATUS_BARS 인데,
            // 이 값을 이용하여 상태표시줄 내용물에 대한 색상에 Light모드일 때 내용물을 어둡게 표현이 가능하다.
            //만약 다크모드에서와 같이 내용물을 밝게 처리하고 싶다면, value영역을 0으로 하여 값을 클리어시킨다.
            val appearance = if (isNightMode()) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            window?.decorView?.windowInsetsController?.setSystemBarsAppearance(appearance, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}