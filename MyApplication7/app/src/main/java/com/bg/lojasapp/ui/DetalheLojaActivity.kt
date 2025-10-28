package com.bg.lojasapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.myapplication.databinding.ActivityDetalheLojaBinding
import com.bg.lojasapp.model.Loja

class DetalheLojaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheLojaBinding
    private lateinit var loja: Loja

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheLojaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }


    private fun loadData() {
        loja = intent.getSerializableExtra("loja") as Loja
    }

    private fun setupViews() {
        binding.tvNome.text = loja.nome
        binding.tvTelefone.text = loja.telefone
        binding.tvCategoria.text = loja.categoria
        binding.imgFoto.setImageResource(loja.foto)
    }

    private fun setupListeners() {
        binding.btnLigar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:${loja.telefone}".toUri()
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

}