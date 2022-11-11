package com.example.myapplication.temp

import com.example.domain.model.Reserves
import com.example.domain.onError
import com.example.domain.onSuccess
import com.example.domain.usecase.remote.GetTempUseCase
import com.example.myapplication.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(
    private var getTempUseCase: GetTempUseCase,
) : BaseViewModel(), TempActionHandler {
    private val _reservesItem: MutableSharedFlow<Reserves> = MutableSharedFlow()
    val reservesItem: SharedFlow<Reserves> = _reservesItem


    fun getReserve() {
        baseViewModelScope.launch {
            getTempUseCase()
                .onSuccess {

                    _reservesItem.emit(it)


                }
                .onError { e ->
                    when (e) {
                        is IOException -> _exception.emit(e)
                        else -> _exception.emit(e)
                    }
                }
        }
    }

    override fun onTempClicked() {
        TODO("Not yet implemented")
    }

}