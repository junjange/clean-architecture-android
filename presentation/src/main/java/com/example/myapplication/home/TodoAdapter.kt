package com.example.myapplication.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.Todo
import com.example.myapplication.databinding.ItemTodoBinding
/**
 * RecyclerViewAdapter 같은 경우, 전체 리스트를 통째로 업데이트해야 할때 100개의 아이템들이 있다면 100개가 모두 업데이트된다.
 * 그런데 안바뀌는 항목이 더 많다면..? 비효율적이다 -> 그래서 DiffUtil 이 있다
 *
 * DiffUtil?
 * 기존 리스트와 업데이트 된 리스트의 차이를 계산하고 실제로 변환할 리스트 아이템들의 결과를 반환하는 유틸리티 클래스
 * 주로 RecyclerView Adpater의 업데이트를 계산하는데 사용되고 ListAdapter에서 DiffUtil을 활용해서 차이점을 계산한다.
 *
 * ListAdapter?
 * 간단히 말해서, DiffUtil을 활용해서 리스트를 업데이트 할 수 있는 기능을 추가한 Adapter
 * AsyncListDiffer 클래스를 쉽게 사용하기 위해 ListAdapter를 활용할 수 있다.
 * ListAdapter의 생성자에서 DiffUtil.ItemCallback을 인자로 넘겨주면서 사용한다.
 * List 데이터를 표현해주며 List를 백그라운드 스레드에서 diff(차이)를 처리하는 특징이 있다.
 *
 */

class TodoAdapter(
    private val eventListener: HomeActionHandler,
) : ListAdapter<Todo, TodoViewHolder>(TodoItemDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
                eventListener = this@TodoAdapter.eventListener
            }
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    /**
     * DiffUtil.ItemCallback
     * areItemsTheSame() 함수가 먼저 실행이 되고 해당 함수의 결과로 true 가 반환됐을 경우에만 areContentsTheSame() 이 호출된다.
     * 그렇기 때문에 areItemsTheSame() 에는 일반적으로 id 처럼 아이템을 식별할 수 있는 유니크한 값을 비교하고,
     * areContentsTheSame() 에는 아이템의 내부 정보가 모두 동일한지 비교한다.
     * 그래서 areContentsTheSame() 에서는 보통 equals() 함수를 활용한한.
     *
     * */

    internal object TodoItemDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem.equals(newItem)
    }
}