package com.example.routinepractice.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ListDao {
    // 데이터에 접근 할 수 있는 메서드를 정의해놓은 interface
    @Insert
    fun insert(listItem: ListItem)

    @Query("SELECT * from ListItem")
    fun getAll(): LiveData<List<ListItem>>
}