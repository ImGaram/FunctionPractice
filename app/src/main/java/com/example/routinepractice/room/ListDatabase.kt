package com.example.routinepractice.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListItem::class], version = 1)
abstract class ListDatabase: RoomDatabase() {
    // 데이터베이스를 생성하고 관리한다.
    abstract fun listDao(): ListDao

    companion object {
        private var instance: ListDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ListDatabase? {
            if (instance == null) {
                synchronized(ListDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                    ListDatabase::class.java, "list").build()
                }
            }
            return instance
        }
    }
}