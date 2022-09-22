package com.example.ratepco2.util.enumerator

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.ratepco2.R

enum class TransportEnum {
    CAR {
        override fun getText(context: Context): String =
            context.getString(R.string.item_menu_with_text_car)
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_car)
    },
    MOTORCYCLE {
        override fun getText(context: Context): String =
            context.getString(R.string.item_menu_with_text_motorcycle)
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_motorcycle)
    },
    BIKE {
        override fun getText(context: Context): String =
            context.getString(R.string.item_menu_with_text_bike)
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_bike)
    },
    ELECTRIC_BIKE {
        override fun getText(context: Context): String =
            context.getString(R.string.item_menu_with_text_electric_bike)

        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_electric_bike)
    },
    BUS {
        override fun getText(context: Context): String =
            context.getString(R.string.item_menu_with_text_bus)

        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_bus)
    },
    TRAIN {
        override fun getText(context: Context): String =
            context.getString(R.string.item_menu_with_text_train)

        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_train)
    },
    TAXI {
        override fun getText(context: Context): String =
            context.getString(R.string.item_menu_with_text_taxi)

        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_taxi)
    };

    abstract fun getText(context: Context): String
    abstract fun getImage(context: Context): Drawable?
}