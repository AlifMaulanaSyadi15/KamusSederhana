package com.example.kamus

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface KataDao {

    @Insert
    suspend fun tambah(kata: Kata)

    // Latihan lanjutan 1: hapus kata (dipakai oleh geser di tab Daftar)
    @Delete
    suspend fun hapus(kata: Kata)

    // Flow memberi kabar ke layar setiap kali isi tabel berubah
    @Query("SELECT * FROM kata ORDER BY indonesia ASC")
    fun ambilSemua(): Flow<List<Kata>>

    @Query("SELECT * FROM kata WHERE indonesia LIKE :kunci ORDER BY indonesia ASC")
    suspend fun cari(kunci: String): List<Kata>
}
