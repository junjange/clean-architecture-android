package com.example.myapplication.create_todo

import android.database.sqlite.SQLiteException
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.common.base.BaseViewModel
import com.example.domain.model.Todo
import com.example.domain.onError
import com.example.domain.onSuccess
import com.example.domain.usecase.local.CreateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject


/**
 *
 * Inject
 * @Inject는 필드, 생성자, 메서드에 붙여 Component로 부터 의존성 객체를 주입 요청하는 annotation이다.
 * @Inject로 의존성 주입을 요청하면 연결된 Component가 Module로부터 객체를 생성하여 넘겨준다.
 * Component는 @Inject 어노테이션을 의존성 주입할 멤버변수와 생성자에 달아줌으로 DI 대상을 확인할 수 있다
 * 객체(인스턴스)의 생성이 클래스에서 이루어지지 않고, Component가 생성해주기 때문에
 * BoilerPlateCode(보일러 플레이트코드)를 작성할 필요없이 클래스를 테스트하기 수월해 진다.
 *
 *
 * */

// 생성자에 @Inject를 사용해서 CreateTodoViewModel의 인스턴스를 주입하는 방식
// 다음과 같이 클래스의 생성자에서 @Inject 주석을 사용하여 클래스의 인스턴스를 제공하는 방법을 Hilt에 알려줍니다.
// constructor를 통해 CreateTodoUseCase를 부생성자로 임명!! 솔직히 모르겠음..
@HiltViewModel
class CreateTodoViewModel @Inject constructor(
    private val createTodoUseCase: CreateTodoUseCase
)  : BaseViewModel(), CreateTodoActionHandler {

    private val _navigationEvent: MutableSharedFlow<CreateTodoNavigationAction> = MutableSharedFlow()
    val navigationEvent: SharedFlow<CreateTodoNavigationAction> = _navigationEvent

    val titleEvent: MutableStateFlow<String> = MutableStateFlow<String>("")
    val bodyEvent: MutableStateFlow<String> = MutableStateFlow<String>("")

    private val createUiModel: MutableStateFlow<CreateUiModel> = MutableStateFlow<CreateUiModel>(
        CreateUiModel())

    init {
        baseViewModelScope.launch {
            titleEvent.collect { title ->
                createUiModel.update {
                    it.copy(title = title)
                }
            }
        }

        baseViewModelScope.launch {
            bodyEvent.collect { body ->
                createUiModel.update {
                    it.copy(body = body)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateTodoClicked() {
        baseViewModelScope.launch {
            if(createUiModel.value.title.isNotEmpty() && createUiModel.value.body.isNotEmpty()) {
                val todo = Todo(
                    null,
                    title = createUiModel.value.title,
                    body = createUiModel.value.body,
                    cratedAt = LocalDateTime.now().toString(),
                    updatedAt = LocalDateTime.now().toString()
                )
                createTodoUseCase(todo)
                    .onSuccess { _navigationEvent.emit(CreateTodoNavigationAction.NavigateToAdd) }
                    .onError {
                        when(it) {
                            is SQLiteException -> _toastMessage.emit("데이터 베이스 에러가 발생하였습니다.")
                            else -> _toastMessage.emit("시스템 에러가 발생 하였습니다.")
                        }
                    }
            }
            else {
                _navigationEvent.emit(CreateTodoNavigationAction.NavigateToBlankError)
            }
        }

    }
}

data class CreateUiModel(
    val title: String = "",
    val body: String = ""
)