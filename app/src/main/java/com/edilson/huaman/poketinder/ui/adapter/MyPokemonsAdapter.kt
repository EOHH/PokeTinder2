package com.edilson.huaman.poketinder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edilson.huaman.poketinder.data.database.entities.MyPokemonEntity
import com.edilson.huaman.poketinder.databinding.ItemPokemonSavedBinding
import com.edilson.huaman.poketinder.ui.holder.MyPokemonsHolder

class MyPokemonsAdapter(val list: List<MyPokemonEntity>): RecyclerView.Adapter<MyPokemonsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPokemonsHolder {
        val  view = ItemPokemonSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPokemonsHolder(view.root)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyPokemonsHolder, position: Int) {

        val item = list[position]
        holder.bind(item)
    }

}