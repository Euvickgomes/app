package com.bg.lojasapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.bg.lojasapp.data.db.LojaDatabase
import com.bg.lojasapp.model.Loja
import com.bg.lojasapp.databinding.ActivityCadastroBinding


class CadastroLojaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var launcherGaleria: ActivityResultLauncher<Array<String>>
    private lateinit var uriSelecionada: String
    private lateinit var db: LojaDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = LojaDatabase.getInstance(this)
        setupLauncher()
        setupListeners()
    }
    private fun setupLauncher(){
        launcherGaleria = registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) { uri ->
            if (uri != null) {
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                uriSelecionada = uri.toString()
                binding.imgFoto.setImageURI(uri)
            }
        }
    }
    private fun setupListeners() {
        binding.imgFoto.setOnClickListener {
            launcherGaleria.launch(arrayOf("image/*"))
        }
        binding.btnSalvar.setOnClickListener {
            val foto = uriSelecionada.toString()
            val nome = binding.edtNome.text.toString()
            val categoria = binding.edtCategoria.text.toString()
            val telefone = binding.edtTelefone.text.toString()
            if (nome.isNotBlank() && telefone.isNotBlank()) {
                val novaLoja = Loja(
                    foto = foto,
                    nome = nome,
                    categoria = categoria,
                    telefone = telefone
                )
                salvarNoBanco(novaLoja)
            }
            else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnVoltar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
    private fun salvarNoBanco(loja: Loja) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.lojaDao().inserir(loja)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
