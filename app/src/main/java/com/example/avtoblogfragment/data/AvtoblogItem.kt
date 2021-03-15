package com.example.avtoblogfragment.data


import android.graphics.drawable.Drawable
import java.io.Serializable

data class AvtoblogItem(
    var img: Drawable?,
    var brand: String,
    var model: String,
    var year: String,
    var motor: String,
    var kpp: String,
    var motorsize: String,
    var power: String,
    var mileage: String
) : Serializable {}