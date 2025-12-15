package com.bg.lojasapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bg.lojasapp.data.db.LojaDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bg.lojasapp.model.Loja
import com.bg.lojasapp.adapter.LojaAdapter
import com.bg.lojasapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lojas: MutableList<Loja>
    private lateinit var adapter: LojaAdapter
    private lateinit var launcherCadastro: ActivityResultLauncher<Intent>
    private lateinit var db: LojaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupRecyclerView()
        setupLauncherCadastro()
        setupListeners()
    }

    override fun onResume(){
        super.onResume()
        loadData()
    }

    fun loadData(){
        lojas = mutableListOf()
        db = LojaDatabase.getInstance(this)
        lifecycleScope.launch(Dispatchers.IO) {
            lojas = db.lojaDao().listarTodos().toMutableList()
            withContext(Dispatchers.Main) {
                adapter.updateLista(lojas)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = LojaAdapter(lojas) { loja ->
            val intent = Intent(this, DetalheLojaActivity::class.java)
            intent.putExtra("loja", loja)
            startActivity(intent)
        }
        binding.listViewLojas.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }
    private fun setupLauncherCadastro() {
        launcherCadastro = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                loadData()
            }
        }
    }
    private fun setupListeners() {
        binding.btnAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroLojaActivity::class.java)
            launcherCadastro.launch(intent)
        }
        binding.edtFiltro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtro = s.toString().lowercase()
                val filtrados = lojas.filter {
                    it.nome.lowercase().contains(filtro)
                }
                adapter.updateLista(filtrados)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}