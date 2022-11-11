package com.example.myapplication.home


/**
 * HomeFragment 에서 HomeActionHandler 을 상속받아 사용
 * 질문 : 여기서 상속을 받아야만 하는 이유는 무엇일까?!
 *
 * */

interface HomeActionHandler {
    fun onTodoCheckClicked(todoIdx: Int, isChecked: Boolean) // todo 상태 변경
    fun onTodoClicked(todoIdx: Int) // todo 클릭
    fun onCreateTodoClicked() // todo 추가
    fun onDeleteTodoClicked() // todo 삭제
    fun onToggleFab() // floating button 클릭
    fun onKeywordChange(query: String?) // 검색어 반응
    fun onToPagingButtonClicked() // paging button 클릭
}
