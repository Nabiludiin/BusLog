package com.d3if4802.buslog.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.d3if4802.buslog.model.TiketBus
import kotlinx.coroutines.flow.Flow

@Dao
interface TiketDao {
    @Query("SELECT * FROM tiket_bus ORDER BY id DESC")
    fun getAllTiket(): Flow<List<TiketBus>>

    @Query("SELECT * FROM tiket_bus WHERE id = :tiketId")
    fun getTiketById(tiketId: Int): Flow<TiketBus>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTiket(tiket: TiketBus)

    @Update
    suspend fun updateTiket(tiket: TiketBus)

    @Delete
    suspend fun deleteTiket(tiket: TiketBus)
}