package com.example.routinepractice.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItem(
    var title: String,
    var name: String,
    var color: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
