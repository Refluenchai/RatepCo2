package com.example.ratepco2.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.ratepco2.util.enumerator.TransportEnum.*

fun getVehicleTypeText(type: Int, context: Context): String =
    when (type) {
        CAR.ordinal -> CAR.getText(context)
        MOTORCYCLE.ordinal -> MOTORCYCLE.getText(context)
        BIKE.ordinal -> BIKE.getText(context)
        ELECTRIC_BIKE.ordinal -> ELECTRIC_BIKE.getText(context)
        BUS.ordinal -> BUS.getText(context)
        TRAIN.ordinal -> TRAIN.getText(context)
        else -> TAXI.getText(context)
    }

fun getVehicleImage(type: Int, context: Context): Drawable? =
    when (type) {
        CAR.ordinal -> CAR.getImage(context)
        MOTORCYCLE.ordinal -> MOTORCYCLE.getImage(context)
        BIKE.ordinal -> BIKE.getImage(context)
        ELECTRIC_BIKE.ordinal -> ELECTRIC_BIKE.getImage(context)
        BUS.ordinal -> BUS.getImage(context)
        TRAIN.ordinal -> TRAIN.getImage(context)
        else -> TAXI.getImage(context)
    }