package com.example.myapplication.home


import android.database.sqlite.SQLiteException
import com.example.domain.model.Todo
import com.example.domain.onError
import com.example.domain.onSuccess
import com.example.domain.usecase.local.DeleteTodoListUseCase
import com.example.domain.usecase.local.GetTodoListUseCase
import com.example.domain.usecase.local.IsCheckTodoUseCase
import com.example.domain.usecase.local.SearchTodoUseCase
import com.example.myapplication.common.base.BaseViewModel
import com.example.tasam_android.home.HomeNavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Inject
 * 의존성 주입을 요청한다. @Inject를 통해서 주입을 요청하면 Component가 Module로 부터 인스턴스를 넘겨준다.
 * 변수에 달면 Field Injection을 수행하고,
 * 생성자에 달면 Constructor Injection을 수행합니다.
 *
 *
 * */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodoListUseCase: GetTodoListUseCase,
    private val searchTodoUseCase: SearchTodoUseCase,
    private val deleteTodoListUseCase: DeleteTodoListUseCase,
    private val isCheckTodoUseCase: IsCheckTodoUseCase,
) : BaseViewModel(), HomeActionHandler {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow<HomeUiState>(
        HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    private fun startLoading() {
        _homeUiState.value = HomeUiState.Loading
    }

    private fun endLoading() {
        _homeUiState.value = HomeUiState.Success
    }

    var queryEvent: MutableStateFlow<String> = MutableStateFlow<String>("")

    /**
     * Flow, stateFlow, sharedFlow, MutableStateFlow, MutableSharedFlow 차이..
     *
     * 질문
     * 여기서 _navigationEvent는 SharedFlow를 사용하고 _todoListEvent는 MutableStateFlow를 사용한 이유가 궁금합니다.
     *
     * StateFlow와 다르게 sharedFlow는 다음과 같다고 하는데..
     * StateFlow의 일반화된 버전으로 볼 수 있으며, SharedFlow 또한 Flow 와 달리 Hot Stream 이다.
     * 초기값을 가지지 않고, 여러 설정이 가능하기 때문에 주로 이벤트 처리에 사용할 수 있다.
     * sharedFlow 생성 시에
     * replay : 새로운 구독자들에게 이전 이벤트 방출여부 ( 0 = 방출 X , 1 = 방출)
     * extraBufferCapacity : 추가 버퍼 생성 여부 ( 1 = 생성)
     * onBufferOverflow : 버퍼 초과시 처리 여부 (DROP_OLDEST = oldest 데이터 drop)
     * 와 같은 설정을 재정의 하여 사용할 수 있다.
     *
     * 그럼에도 이유를 못차겠어서 여쭤봅니다..
     *
     * 또, 왜 MutableSharedFlow -> SharedFlow/ MutableStateFlow -> StateFlow 로 설정했는지도 궁금합니다.
     * SharedFlow은 상태가 변할 때만 반응(UI 이벤트 처리)
     *
     * StateFlow은 상태가 변할때마다 반응(버튼 이벤트 처리 등)
     * */

    private val _navigationEvent: MutableSharedFlow<HomeNavigationAction> = MutableSharedFlow()
    val navigationEvent: SharedFlow<HomeNavigationAction> = _navigationEvent

    private val _todoListEvent: MutableStateFlow<List<Todo>> = MutableStateFlow(emptyList())
    val todoListEvent: StateFlow<List<Todo>> = _todoListEvent

    var toggleStateEvent: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        baseViewModelScope.launch {
            queryEvent.debounce(500).collectLatest {
                onKeywordChange(it)
            }
        }
    }

    fun getTodoList() {
        startLoading()
        baseViewModelScope.launch {
            getTodoListUseCase()
                .onSuccess { _todoListEvent.value = it }
                .onError { _toastMessage.emit("시스템 에러가 발생하엿씁니다.") }
        }
        endLoading()
    }

    override fun onTodoCheckClicked(todoIdx: Int, isChecked: Boolean) {
        startLoading()
        baseViewModelScope.launch {
            isCheckTodoUseCase(todoIdx)
                .onSuccess { getTodoList() }
                .onError { e ->
                    when(e) {
                        is SQLiteException -> _toastMessage.emit("데이터 베이스 에러가 발생하였습니다.")
                        else -> _toastMessage.emit("시스템 에러가 발생 하였습니다.")
                    }
                }
        }
        endLoading()
    }

    override fun onTodoClicked(todoIdx: Int) {
        baseViewModelScope.launch {
            _navigationEvent.emit(HomeNavigationAction.NavigateToTodoDetail(todoIdx))
            whenToggleState()
        }
    }

    override fun onCreateTodoClicked() {
        baseViewModelScope.launch {
            _navigationEvent.emit(HomeNavigationAction.NavigateToCreate)
            whenToggleState()
        }
    }

    override fun onDeleteTodoClicked() {
        startLoading()
        baseViewModelScope.launch {
            deleteTodoListUseCase()
                .onSuccess {
                    _todoListEvent.value = emptyList()
                    whenToggleState() }
                .onError { e ->
                    when(e) {
                        is SQLiteException -> _toastMessage.emit("데이터 베이스 에러가 발생하였습니다.")
                        else -> _toastMessage.emit("시스템 에러가 발생 하였습니다.")
                    }
                }
        }
        endLoading()
    }

    private fun whenToggleState() {
        if(toggleStateEvent.value) {
            toggleStateEvent.value = !toggleStateEvent.value
        }
    }

    override fun onToggleFab() {
        toggleStateEvent.value = !toggleStateEvent.value
    }

    override fun onKeywordChange(query: String?) {
        startLoading()
        baseViewModelScope.launch {
            if(query == null) {
                getTodoList()
            } else {
                searchTodoUseCase(query)
                    .onSuccess { _todoListEvent.value = it }
                    .onError { e ->
                        when(e) {
                            is SQLiteException -> _toastMessage.emit("데이터 베이스 에러가 발생하였습니다.")
                            else -> _toastMessage.emit("시스템 에러가 발생 하였습니다.")
                        }
                    }
            }
        }
        endLoading()
    }

    override fun onToPagingButtonClicked() {
        baseViewModelScope.launch {
            _navigationEvent.emit(HomeNavigationAction.NavigateToPaging)
            whenToggleState()
        }
    }

}

sealed class HomeUiState {
    object Loading: HomeUiState()
    object Success: HomeUiState()
}