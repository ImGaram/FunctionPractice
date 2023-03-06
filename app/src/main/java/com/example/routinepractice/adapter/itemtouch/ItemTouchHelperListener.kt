package com.example.routinepractice.adapter.itemtouch

// 아이템의 움직임이 감지되면 호출하는 메서드
interface ItemTouchHelperListener {
    fun onItemMove(from: Int, to: Int)
}