package com.example.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    val title: String,
    val body: String,
    val cratedAt: String,
    val updatedAt: String,
    var isChecked: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var todoIdx: Int = 0
}