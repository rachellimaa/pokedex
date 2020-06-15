package com.example.initkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
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

        Places.initialize(getApplicationContext(), BuildConfig.GOOGLE_API_KEY);
        configureRecyclerView()
        shouldDisplayEmptyView(pokemons.isEmpty())
        fabAddPokemon.setOnClickListener{
            val intent = Intent(this, AddPokemonActivity::class.java)
            startActivityForResult(intent, ADD_POKEMON_REQUEST_CODE)
        }
    }

    private fun configureRecyclerView() {
        recycler_view_pokemon.adapter = PokemonAdapter(pokemons) {
            val intent = Intent(this, PokemonDetailActivity::class.java).apply {
                putExtra(PokemonDetailActivity.POKEMON_EXTRA, it)
            }
            startActivity(intent)
        }
    }

    private fun shouldDisplayEmptyView(isEmpty: Boolean) {
        val visibility = if (isEmpty) View.VISIBLE else View.GONE
        emptyView.visibility = visibility
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_POKEMON_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //todo: adiciona na lista um pokemon do resultado
        }
    }

    companion object{
        const val ADD_POKEMON_REQUEST_CODE = 1
    }
}