package com.example.routinepractice.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.routinepractice.dao.ProcedureDao
import com.example.routinepractice.item.ProcedureItem

@Database(entities = [ProcedureItem::class], version = 1)
abstract class ProcedureDatabase: RoomDatabase() {
    abstract fun procedureDao(): ProcedureDao

    companion object {
        private var instance: ProcedureDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ProcedureDatabase? {
            if (instance == null) {
                synchronized(ProcedureDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, ProcedureDatabase::class.java, "procedure")
                        .build()
                }
            }
            return instance
        }
    }
}

//private val MIGRATION_1_2: Migration = object :Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.run {
//            execSQL("update procedure set checkAble=true")
//            execSQL("update procedure set checkAble=false")
//        }
//    }
//}