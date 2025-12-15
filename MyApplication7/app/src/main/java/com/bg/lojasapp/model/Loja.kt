package com.bg.lojasapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "loja")
data class Loja(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val foto: String,
    val nome: String,
    val categoria: String,
    val telefone: String
) : Serializable