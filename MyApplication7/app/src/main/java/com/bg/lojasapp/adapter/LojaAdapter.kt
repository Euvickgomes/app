package com.bg.lojasapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bg.lojasapp.model.Loja
import com.example.myapplication.databinding.ItemLojaBinding


class LojaAdapter(
    private val context: Context,
    private val lista: List<Loja>
) : ArrayAdapter<Loja>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemLojaBinding
        val itemView: View

        if (convertView == null) {
            binding = ItemLojaBinding.inflate(LayoutInflater.from(context), parent, false)
            itemView = binding.root
            itemView.tag = binding
        } else {
            itemView = convertView
            binding = itemView.tag as ItemLojaBinding
        }

        val loja = lista[position]

        binding.imgFoto.setImageResource(loja.foto)
        binding.tvNome.text = loja.nome
        binding.tvTelefone.text = loja.telefone
        binding.tvCategoria.text = loja.categoria

        return itemView
    }

}