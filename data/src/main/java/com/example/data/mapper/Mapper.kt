package com.example.data.mapper

import com.example.data.model.local.TodoEntity
import com.example.data.model.remote.ReservesInfoResponseData
import com.example.domain.model.Reserves
import com.example.domain.model.Todo

fun Todo.toData(): TodoEntity {
    return TodoEntity(
        title = this.title,
        body = this.body,
        cratedAt = this.cratedAt,
        updatedAt = this.updatedAt
    )
}

fun List<TodoEntity>.toDomain(): List<Todo> {
    return map { Todo(
        todoIdx = it.todoIdx,
        title = it.title,
        body = it.body,
        cratedAt = it.cratedAt,
        updatedAt = it.updatedAt,
        isChecked = it.isChecked
    ) }
}

fun TodoEntity.toDomain(): Todo {
    return Todo(
        todoIdx = this.todoIdx,
        title = this.title,
        body = this.body,
        cratedAt = this.cratedAt,
        updatedAt = this.updatedAt,
        isChecked = this.isChecked
    )
}

fun ReservesInfoResponseData.toDomain() : Reserves {

    return Reserves(
        reserveId = this.reserveId,
        sport = this.sport,
        reserveDate = this.reserveDate,
        startT = this.startT,
        endT = this.endT,
        title = this.title,
        explanation = this.explanation,
        currentNum = this.currentNum,
        recruitmentNum = this.recruitmentNum,
        place = this.place,
        gender = this.gender,
        reserveStatus = this.reserveStatus,
        userId = this.userId,
        schoolNum = this.schoolNum,
        name = this.name
    )

}