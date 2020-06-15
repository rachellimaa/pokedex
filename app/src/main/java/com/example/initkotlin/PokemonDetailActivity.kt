package com.example.initkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        intent.getParcelableExtra<Pokemon>(POKEMON_EXTRA)?.let {pokemon ->
            name_pokemon_detail.text = pokemon.name
            id_pokemon_detail.text = "#%03d".format(pokemon.number)
            type_pokemon_detail.text = pokemon.types.getOrNull(0)
            height_pokemon.text = "#%.2f cm".format(pokemon.height / 10)
            weight_pokemon.text = "#%.1f kg".format(pokemon.weight)
            Picasso.get().load(pokemon.imageURL).into(image_pokemon_detail)
            val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
            mapFragment.getMapAsync{
                it.uiSettings.isZoomControlsEnabled = true
                val LatLng = LatLng(pokemon.latitude, pokemon.longitude)
                val marker = MarkerOptions()
                    .position(LatLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.location))
                it.addMarker(marker)
                it.moveCamera(CameraUpdateFactory.newLatLng(LatLng))
                it.moveCamera(CameraUpdateFactory.zoomTo(15f))
            }
        }
    }

    companion object
    {
        const val POKEMON_EXTRA = ""
    }
}