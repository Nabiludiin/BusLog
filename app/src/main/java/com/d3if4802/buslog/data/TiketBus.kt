package com.d3if4802.buslog.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tiket_bus")
data class TiketBus(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val poBus: String,
    val asal: String,
    val tujuan: String,
    val tanggal: String,
    val harga: Int,
    val nomorKursi: String
)