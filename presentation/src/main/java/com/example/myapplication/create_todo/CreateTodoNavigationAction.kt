package com.example.myapplication.create_todo

sealed class CreateTodoNavigationAction {
    object NavigateToInit: CreateTodoNavigationAction()
    object NavigateToAdd : CreateTodoNavigationAction()
    object NavigateToError: CreateTodoNavigationAction()
    object NavigateToBlankError: CreateTodoNavigationAction()
}