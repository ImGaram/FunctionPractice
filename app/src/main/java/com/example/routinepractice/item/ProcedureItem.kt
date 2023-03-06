package com.example.routinepractice.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProcedureItem(
    val title: String,
    val name: String,
    var checkAble: Boolean = false
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
