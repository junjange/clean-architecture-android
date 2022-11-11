package com.example.tasam_android.home

/**
 * sealed class란
 * sealed class란 추상 클래스(abstract class)로 상속 받는 자식 클래스(child class)의 종류를 제한하는 특성을 가지고 있다.
 * 즉, 컴파일러에서 sealed class의 자식 클래스가 어떤 것이 있는지 알 수 있다.
 *
 *
 * 질문! NavihateToTodoDeatail만 class인 이유가 있을까요? 뭔가 파라미터때문에인 것 같은데!!..
 *
 * */
sealed class HomeNavigationAction {
    object NavigateToInit: HomeNavigationAction()
    class NavigateToTodoDetail(val todoIdx: Int) : HomeNavigationAction()
    object NavigateToCreate: HomeNavigationAction()
    object NavigateToPaging: HomeNavigationAction()
}