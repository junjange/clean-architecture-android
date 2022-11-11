package com.example.myapplication.detail

sealed class DetailNavigationActions {
    object NavigateDelete : DetailNavigationActions()
    object NavigatePatch : DetailNavigationActions()
    object NavigateBlankError : DetailNavigationActions()
}