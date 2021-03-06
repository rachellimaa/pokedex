package com.example.initkotlin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_pokemon.view.*

class PokemonAdapter(
    private val pokemons: List<Pokemon>,
    private val onItemClick: (Pokemon) -> Unit
) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun getItemCount() = pokemons.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.itemView.name_pokemon.text = pokemons[position].name
        holder.itemView.id_pokemon.text = "#%03d".format(pokemons[position].number)
        Picasso.get().load(pokemons[position].imageURL).into(holder.itemView.image_pokemon)

        holder.itemView.setOnClickListener {
            onItemClick(pokemons[position])
        }
    }
}