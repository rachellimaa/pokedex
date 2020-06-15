package com.example.initkotlin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    var name: String,
    var number: Int,
    var types : List<String>,
    var imageURL : String,
    var weight : Float,
    var height : Float,

    // Maps
    var latitude : Double,
    var longitude : Double
) : Parcelable