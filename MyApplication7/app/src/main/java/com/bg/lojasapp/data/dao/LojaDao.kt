package com.bg.lojasapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bg.lojasapp.model.Loja

import java.util.List;

@Dao
interface LojaDao {
    @Insert
    suspend fun inserir(loja: Loja)
    @Query("SELECT * FROM loja ORDER BY nome ASC")
    suspend fun listarTodos(): List<Loja>
    @Query("SELECT * FROM loja WHERE nome LIKE :filtro ORDER BY nome ASC")
    suspend fun filtrarPorNome(filtro: String): List<Loja>
    @Delete
    suspend fun deletar(loja: Loja)
    @Update
    suspend fun atualizar(loja: Loja)
}
