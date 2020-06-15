package com.example.initkotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.activity_add_pokemon.*

class AddPokemonActivity : AppCompatActivity() {
    private lateinit var place: Place

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pokemon)

        btnDone.setOnClickListener {
            if (textInputName.text.isNullOrBlank() && textInputName.text.isNullOrEmpty() &&
                    textInputLocale.text.isNullOrEmpty() && textInputLocale.text.isNullOrBlank()){
                // todo: request poke api
            }else{
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
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE && data != null){
            if(resultCode == Activity.RESULT_OK){
                place = Autocomplete.getPlaceFromIntent(data)
                textInputLocale.setText(place.address)
            }
        }

    }

    companion object{
        const val AUTOCOMPLETE_REQUEST_CODE = 1
    }
}