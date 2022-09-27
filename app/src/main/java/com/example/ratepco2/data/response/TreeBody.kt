package com.example.ratepco2.data.response

import com.google.gson.annotations.SerializedName

data class TreeBody(
    @SerializedName("number Of Trees")
    val numberOfTrees: Double,
    @SerializedName("success")
    val success: Boolean
)
