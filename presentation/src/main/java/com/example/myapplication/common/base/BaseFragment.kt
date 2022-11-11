package com.example.myapplication.common.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.common.widget.DefaultDialogFragment
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


/**
 *
 * BaseFragment도 반복적이고 공통적인 코드를 조금이나마 재사용과 간결하게 하기위해서 사용한다.
 *
 * protected : 상속받은 클래스에서 사용할 수 있다.
 *
 * open
 * 자바에서는 클래스에 final이 붙지 않으면 모두 다른 클래스에서 상속이 가능하다.
 * 하지만 코틀린에서의 클래스와 메서드는 기본적으로 final이다.
 * 따라서 어떤 클래스의 상속을 허용하려면 해당 클래스 앞에 open 변경자를 붙여야 한다.
 * 그와 더불어 오버라이드를 허용하고 싶은 메서드나 프로퍼티의 앞에도 open 변경자를 붙여야 한다.
 *
 * abstract
 * 상속이 가능한 open클래스와 다르게 추상클래스는 상속이 필수적이다. 즉 얘 자체로 객체를 만들어서 쓰기 위함이 아니라 상속을 위한 클래스.
 *
 *
 * */

open class  BaseFragment : Fragment() {

    /**
     * 안드로이드에서는 라이프사이클에 종속되어 있어 관리가 쉽고, 옵저버패턴을 구현할 수 있게 해주는 LiveData를 사용했다.
     * 하지만 Flow는 LiveData 보다 여러 장점이 있기 때문에 Flow를 사용하는 것이 권장된다고 한다.
     * Flow를 살펴보면서 그 이유에 대해 알아보려고 한다.
     *
     * Flow란?
     * 순차적으로 값을 배출하고 정상 또는 예외 처리하는 비동기 데이터 스트림
     *
     * 기본적인 Flow의 구조는 emit() 으로 데이터를 배출하고, Collect 로 데이터를 받아오는 방식이다. 위 과정은 비동기로 처리된다.
     * Flow는 Livedata와 달리 3가지 차이가 존재한다.
     *
     * 1️⃣ 상태(Value)를 가지지 않는다.
     * 2️⃣ Cold Stream 방식이기 때문에, 연속으로 데이터를 처리할 때마다 Flow를 호출하게 된다.
     * 3️⃣ 스스로 라이프사이클을 알지 못한다.
     *
     * Cold Stream :
     * 하나의 소비자가 구독 시 생성되며, 소비할 때마다 생성되어 데이터가 발행된다.
     * 예를 들어 상태가 변하지 않는 값을 읽을 때 데이터베이스를 읽거나 URL을 통해서 서버 값을 읽는 경우 Cold Stream으로 구현할 수 있다.
     *
     * Hot Stream :
     * 하나 이상의 소비자들이 구독할 수 있고, 데이터 발행이 시작되면 모든 소비자들에게 같은 데이터를 발행한다.
     * 예를 들어 상태가 변하는 값을 읽을 때 네트워크 상태 정보 값을 얻어올 때 Hot Stream을 사용할 수 있다.
     *
     * 위 단점들을 보완하기 위해
     * 1,2번 보완 => StateFlow, SharedFlow
     * 3번 보완 => launchWhenStarted
     * 를 사용할 수 있다.
     *
     * StateFlow는 SharedFlow를 상속받고, SharedFlow는 Flow를 상속받는 관계이다.
     * 또한 3번 문제를 보완하기 위해 Android Arctic fox 버전부터 데이터바인딩 시에 Flow를 LiveData와 동일하게 라이프사이클에 종속시킬 수 있다.
     *
     * StateFlow의 일반화된 버전으로 볼 수 있으며, SharedFlow 또한 Flow 와 달리 Hot Stream 이다.
     * 초기값을 가지지 않고, 여러 설정이 가능하기 때문에 주로 이벤트 처리에 사용할 수 있다.
     * sharedFlow 생성 시에
     * replay : 새로운 구독자들에게 이전 이벤트 방출여부 ( 0 = 방출 X , 1 = 방출)
     * extraBufferCapacity : 추가 버퍼 생성 여부 ( 1 = 생성)
     * onBufferOverflow : 버퍼 초과시 처리 여부 (DROP_OLDEST = oldest 데이터 drop)
     * 와 같은 설정을 재정의 하여 사용할 수 있다.
     *
     * 참고 : https://hanyeop.tistory.com/m/377
     * */

    protected var exception: SharedFlow<Exception>? = null
    protected var toastMessage: SharedFlow<String>? = null
    protected var toast: Toast? = null

    // ?????
    protected val isSafe: Boolean
        get() = !(this.isRemoving || this.activity == null || this.isDetached || !this.isAdded || this.view == null || !this.isVisible)


    // 앱 종료시
    protected open fun onApplicationFinishRequired() {
        ActivityCompat.finishAffinity(requireActivity())
    }

    // 화면 종료시
    protected open fun onFragmentFinishRequired() {
        requireActivity().finish()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        // lifecycleScope.lauch를 사용하는 경우, 작업의 스코프를 View의 특정 인스턴스로 지정할 수 있다.
        // launchWhenResumed, launchWhenStarted, launchWhenCreated를 사용하여 특정 라이프사이클 상태로 작업을 제한한 경우,
        // 해당 상태로 범위를 좁힐 수 있다.
        lifecycleScope.launchWhenStarted {
            launch {
                exception?.collect { exception ->
                    onError(exception)
                }
            }

            launch {
                toastMessage?.collect { message ->
                    showToastMessage(message)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        toast?.cancel()
    }

    protected fun onError(throwable: Throwable) {
        showNetworkErrorDialog()
    }

    private fun showServiceErrorDialog(throwable: Throwable) {
        val message = throwable.cause?.message ?: getString(R.string.unknown_error_message)
        showErrorDialog(message) { onFragmentFinishRequired() }
    }

    private fun showNetworkErrorDialog() {
        DefaultDialogFragment.Builder()
            .message(getString(R.string.network_error_message))
            .positiveButton(getString(R.string.retry), object : DefaultDialogFragment.OnClickListener {
                override fun onClick() {
                    onApplicationFinishRequired()
                }
            })
            .negativeButton(getString(R.string.exit), object : DefaultDialogFragment.OnClickListener {
                override fun onClick() {
                    onApplicationFinishRequired()
                }
            })
            .cancelable(false)
            .build()
            .show(childFragmentManager, "network_error_dialog")
    }

    private fun showErrorDialog(message: String, callback: (() -> Unit)) {
        DefaultDialogFragment.Builder()
            .message(message)
            .positiveButton(getString(R.string.confirm), object : DefaultDialogFragment.OnClickListener {
                override fun onClick() {
                    callback.invoke()
                }
            })
            .cancelable(false)
            .build()
            .show(childFragmentManager, "error_dialog")
    }

    private fun showUnknownErrorDialog() {
        val message = getString(R.string.unknown_error_message)
        showErrorDialog(message) { onApplicationFinishRequired() }
    }

    fun showToastMessage(messageResId: Int) {
        toast?.cancel()
        toast = Toast.makeText(activity, messageResId, Toast.LENGTH_LONG)?.apply { show() }
    }

    fun showToastMessage(message: String) {
        toast?.cancel()
        toast = Toast.makeText(activity, message, Toast.LENGTH_LONG)?.apply { show() }
    }

    fun Fragment.navigate(directions: NavDirections) {
        val controller = findNavController()
        val currentDestination = (controller.currentDestination as? FragmentNavigator.Destination)?.className
            ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className
        if (currentDestination == this.javaClass.name) {
            controller.navigate(directions)
        }
    }
}