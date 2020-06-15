package com.example.initkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        intent.getParcelableExtra<Pokemon>(POKEMON_EXTRA)?.let {
            name_pokemon_detail.text = it.name
            id_pokemon_detail.text = "#%03d".format(it.number)
            type_pokemon_detail.text = it.types.getOrNull(0)
            height_pokemon.text = "#%.2f cm".format(it.height / 10)
            weight_pokemon.text = "#%.1f kg".format(it.weight)
            Picasso.get().load(it.imageURL).into(image_pokemon_detail)
        }
    }

    companion object
    {
        const val POKEMON_EXTRA = ""
    }
}