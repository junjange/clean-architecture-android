package com.example.myapplication.home

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Todo
import com.example.myapplication.databinding.ItemTodoBinding

class TodoViewHolder(
    private val binding: ItemTodoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Todo) {
        binding.apply {
            model = item
            executePendingBindings()
        }
    }
}