package com.example.myapplication.detail

import android.database.sqlite.SQLiteException
import com.example.myapplication.common.base.BaseViewModel
import com.example.domain.onError
import com.example.domain.onSuccess
import com.example.domain.usecase.local.DeleteTodoUseCase
import com.example.domain.usecase.local.GetTodoUseCase
import com.example.domain.usecase.local.UpdatePostLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getTodoUseCase: GetTodoUseCase,
    private val updatePostUseCase: UpdatePostLocalUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
)  : BaseViewModel(), DetailActionHandler {

    val todoIdxEvent = MutableStateFlow<Int>(0)

    val titleEvent: MutableStateFlow<String> = MutableStateFlow<String>("")
    val bodyEvent: MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _navigationEvent : MutableSharedFlow<DetailNavigationActions> = MutableSharedFlow<DetailNavigationActions>()
    val navigationEvent : SharedFlow<DetailNavigationActions> = _navigationEvent

    init {
    }

    fun getTodo() {
        baseViewModelScope.launch {
            val todo = getTodoUseCase(todoIdxEvent.value).onSuccess { todo ->
                titleEvent.value = todo.title
                bodyEvent.value = todo.body
            }
        }
    }

    override fun onDeleteClicked() {
        baseViewModelScope.launch {
            deleteTodoUseCase.invoke(todoIdxEvent.value)
                .onSuccess {
                    _navigationEvent.emit(DetailNavigationActions.NavigateDelete)
                }.onError {
                    _toastMessage.emit("삭제가 정상적으로 처리되지 않았습니다. ${it.message}")
                }
        }
    }

    override fun onPatchClicked() {
        baseViewModelScope.launch {
            if(titleEvent.value.isEmpty() || bodyEvent.value.isEmpty()){
                _navigationEvent.emit(DetailNavigationActions.NavigateBlankError)
            }else{
                updatePostUseCase.invoke(todoIdxEvent.value,titleEvent.value,bodyEvent.value)
                    .onSuccess {
                        _navigationEvent.emit(DetailNavigationActions.NavigatePatch)
                    }.onError { e->
                        when(e){
                            is SQLiteException -> _toastMessage.emit("데이터 베이스 에러가 발생하였습니다.")
                            else -> _toastMessage.emit("수정이 정상적으로 처리되지 않았습니다.")
                        }
                    }
            }

        }
    }
}
