package com.example.initkotlin

data class Pokemon(
    val name: String,
    val number: Int,
    val types : List<String>,
    val imageURL : String,
    val weight : Float,
    val height : Float,

    // Maps
    val latitude : Double,
    val longitude : Double
)