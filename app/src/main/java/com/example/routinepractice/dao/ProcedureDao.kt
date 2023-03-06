package com.example.routinepractice.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.routinepractice.item.ProcedureItem

@Dao
interface ProcedureDao {
    @Insert
    fun insert(procedureItem: ProcedureItem)

    @Update
    fun update(procedureItem: ProcedureItem)

    @Query("select * from ProcedureItem")
    fun getAll(): LiveData<List<ProcedureItem>>

    @Query("delete from ProcedureItem")
    fun deleteAll()
}