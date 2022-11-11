package com.example.myapplication.home

import android.graphics.Paint
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Todo
import com.example.myapplication.R


/**
 * BindingAdpater란?
 * 뷰의 속성을 설정하는 메서드는 여러 가지가 있다.
 * 당장 텍스트 뷰만 해도 텍스트 크기, 텍스트 컬러, 높이, 여백 등등 무수히 많은 옵션들이 있으니 말이다.
 * 하. 지. 만. 그럼에도 불구하고 내가 원하는 기능의 메서드가 없다면 어떻게 해야 할까?
 * 액티비티에서 내가 원하는 메서드를 만들어 사용하듯이 레이아웃에서도 내가 원하는 메서드를 만들어 사용할 수는 없을까?
 * 결론부터 말하자면 할 수 있다. Binding Adapter를 사용하면 된다.
 *
 * 그럼 이부분은 Base에 넣어도 되지 않을까? 라는 개인적인 생각이빈다..
 * 
 * */

@BindingAdapter("todoAdapter")
fun RecyclerView.bindTodo(itemList: List<Todo>) {
    val boundAdapter = this.adapter
    if (boundAdapter is TodoAdapter) {
        boundAdapter.submitList(itemList)
    }
}

@BindingAdapter("isLoading")
fun ProgressBar.bindIsLoading(state: HomeUiState) {
    when(state) {
        is HomeUiState.Loading -> this.visibility = View.VISIBLE
        is HomeUiState.Success -> this.visibility = View.GONE
    }
}

@BindingAdapter("isDone")
fun TextView.bindIsDone(isChecked: Boolean) {
    if(isChecked) {
        this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        this.setTextColor(ContextCompat.getColor(context, R.color.n3))
    } else {
        this.paintFlags = 0
        this.setTextColor(ContextCompat.getColor(context, R.color.black))
    }
}