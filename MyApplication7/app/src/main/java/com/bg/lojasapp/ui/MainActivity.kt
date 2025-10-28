package com.bg.lojasapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.bg.lojasapp.model.Loja
import com.bg.lojasapp.adapter.LojaAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lojas: List<Loja>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }

    fun loadData(){
        lojas = listOf(
            Loja(
                R.drawable.pikapau,
                "Pesqueiro Pika Pau",
                "Pesqueiro",
                "(16) 99614-2008"
            ),
            Loja(
                R.drawable.piznovosabor,
                "Pizzaria Novo Sabor",
                "Pizzaria",
                "(16) 99160-2192"
            ),
            Loja(
                R.drawable.blackbelt,
                "Black Belt",
                "Academia de Luta",
                "(16) 99704-8080"
            ),
            Loja(
                R.drawable.ruscito,
                "Supermercados Ruscito",
                "Supermercado",
                "(16) 99994-5610"
            ),
            Loja(
                R.drawable.blackfit,
                "Black Fit",
                "Academia",
                "(16) 99257-9697"
            ),
            Loja(
                R.drawable.drogariatotal,
                "Drogaria Total",
                "Farmacia",
                "(16) 99779-5677"
            )
        ).sortedBy { it.nome }
    }

    fun setupViews(){
        val adapter = LojaAdapter(this, lojas)
        binding.listViewContatos.adapter = adapter
    }

    fun setupListeners() {
        binding.listViewContatos.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetalheLojaActivity::class.java)
            intent.putExtra("loja", lojas[position])
            startActivity(intent)
        }
    }
}