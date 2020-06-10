package com.example.initkotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemons: List<String>  = listOf("Pikachu", "Voltorb")
        recycler_view_pokemon.adapter = PokemonAdapter (pokemons)

        shouldDisplayEmptyView(pokemons.isEmpty())
    }

    private fun shouldDisplayEmptyView(isEmpty: Boolean) {
        // val nunca muda o valor, var muda o valor
        val visibility = if (isEmpty) View.VISIBLE else View.GONE
        emptyView.visibility = visibility
    }


}