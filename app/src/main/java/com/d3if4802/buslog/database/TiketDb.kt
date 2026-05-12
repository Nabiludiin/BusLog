package com.d3if4802.buslog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.d3if4802.buslog.model.ProfileEntity
import com.d3if4802.buslog.model.TiketBus

@Database(entities = [TiketBus::class, ProfileEntity::class], version = 2, exportSchema = false)
abstract class TiketDb : RoomDatabase() {
    abstract val dao: TiketDao

    companion object {
        @Volatile
        private var INSTANCE: TiketDb? = null

        fun getInstance(context: Context): TiketDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TiketDb::class.java,
                        "tiket.db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}