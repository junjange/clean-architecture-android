package com.example.domain.model

data class Todo(
    val todoIdx: Int?,
    val title: String,
    val body: String,
    val cratedAt: String,
    val updatedAt: String,
    var isChecked: Boolean = false
)