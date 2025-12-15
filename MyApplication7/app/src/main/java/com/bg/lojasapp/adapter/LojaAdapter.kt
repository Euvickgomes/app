package com.bg.lojasapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bg.lojasapp.model.Loja
import com.bg.lojasapp.databinding.ItemLojaBinding


class LojaAdapter(
    private var lojas: List<Loja>,
    private val onClick: (Loja) -> Unit
) : RecyclerView.Adapter<LojaAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLojaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lojas[position])
    }

    override fun getItemCount(): Int = lojas.size

    fun updateLista(novasLojas: List<Loja>) {
        this.lojas = novasLojas
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemLojaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loja: Loja) {
            binding.imgFoto.setImageURI(loja.foto.toUri())
            binding.tvNome.text = loja.nome
            binding.tvTelefone.text = loja.telefone
            binding.tvCategoria.text = loja.categoria
            binding.root.setOnClickListener { onClick(loja) }
        }
    }

}