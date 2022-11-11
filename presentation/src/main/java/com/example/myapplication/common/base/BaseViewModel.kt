package com.example.myapplication.common.base

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


/**
 * BaseActivity와 마찬가지로 ViewModel에서 기본적으로 필요한 변수와 함수를 정의한 파일이다
 * */
open class BaseViewModel : ViewModel() {

    protected val _exception = MutableSharedFlow<Exception>()
    val exception: SharedFlow<Exception>
        get() = _exception

    protected val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String>
        get() = _toastMessage

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch(coroutineContext) {
            _exception.emit(throwable as Exception)
        }
    }

    protected val baseViewModelScope: CoroutineScope = viewModelScope + errorHandler
}
