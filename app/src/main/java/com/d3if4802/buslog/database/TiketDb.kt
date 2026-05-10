package com.d3if4802.buslog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.d3if4802.buslog.model.TiketBus

@Database(entities = [TiketBus::class], version = 1, exportSchema = false)
abstract class TiketDb : RoomDatabase() {

    abstract val dao: TiketDao

    companion object {
        @Volatile
        private var INSTANCE: TiketDb? = null

        fun getInstance(context: Context): TiketDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TiketDb::class.java,
                    "tiket_bus.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}