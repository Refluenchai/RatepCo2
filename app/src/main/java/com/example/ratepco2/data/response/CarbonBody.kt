package com.example.ratepco2.data.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class CarbonBody(
    @SerializedName("carbon")
    val carbon: String,
    @SerializedName("success")
    val success: Boolean
)

