package com.bg.lojasapp.model

import java.io.Serializable

data class Loja(
    val foto: Int,
    val nome: String,
    val categoria: String,
    val telefone: String
) : Serializable