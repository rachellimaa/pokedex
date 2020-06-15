package com.example.initkotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.activity_add_pokemon.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddPokemonActivity : AppCompatActivity() {
    private lateinit var place: Place

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pokemon)

        btnDone.setOnClickListener {
            if (!textInputName.text.isNullOrEmpty() && !textInputLocale.text.isNullOrEmpty()) {
                val text = textInputName.text.toString().toLowerCase().trim()
                PokeApi.INSTANCE.getPokemonByName(text).enqueue(object : Callback<Pokemon> {
                    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                        Log.d("TAG", "Failure" + t.message)
                    }

                    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                        if (response.isSuccessful) {
                            Log.d("TAG", "Success" + response.toString())
                            val pokemon = response.body()?.apply {
                                latitude = place.latLng?.latitude ?: 0.0
                                longitude = place.latLng?.longitude ?: 0.0
                            }
                            val intent = Intent().apply {
                                putExtra(ADD_POKEMON_EXTRA, pokemon)
                            }
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                        } else {
                            Log.d("TAG", "Error " + response.toString())
                        }
                    }
                })
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT)
            }
        }

        setupClickLocale()

    }

    private fun setupClickLocale() {
        textInputLocale.setOnClickListener {
            val fields = listOf(Place.Field.ADDRESS, Place.Field.LAT_LNG)
            val intent =
                Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this)
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE && data != null) {
            if (resultCode == Activity.RESULT_OK) {
                place = Autocomplete.getPlaceFromIntent(data)
                textInputLocale.setText(place.address)
            }
        }

    }

    companion object {
        const val AUTOCOMPLETE_REQUEST_CODE = 1
        const val ADD_POKEMON_EXTRA = "AddedPokemon"
    }
}