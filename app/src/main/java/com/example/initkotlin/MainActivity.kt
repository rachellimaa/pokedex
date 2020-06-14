package com.example.initkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // lateinit -> Initialize later
    private var pokemons: List<Pokemon> = listOf(
        Pokemon(
            name = "Pikachu",
            number = 25,
            types = listOf("Eletric"),
            imageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            weight = 6f,
            height = 4f,
            latitude = -3.1028263,
            longitude = -60.0147652
        ),
        Pokemon(
            name = "Squirtle",
            number = 7,
            types = listOf("Water"),
            imageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
            weight = 8.5f,
            height = 6f,
            latitude = -1.9928572,
            longitude = -60.8552653
        )
    )

    /*get() {
        return field
    }
    set(value) {
        println("Use Set")
        field = value
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view_pokemon.adapter = PokemonAdapter(pokemons) {
            startActivity(Intent(this, PokemonDetailActivity::class.java))
        }
        shouldDisplayEmptyView(pokemons.isEmpty())
    }

    private fun shouldDisplayEmptyView(isEmpty: Boolean) {
        val visibility = if (isEmpty) View.VISIBLE else View.GONE
        emptyView.visibility = visibility
    }
}