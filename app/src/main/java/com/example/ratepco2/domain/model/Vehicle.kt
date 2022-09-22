package com.example.ratepco2.domain.model

import java.io.Serializable

data class Vehicle(
    val type: Int = 0,
    val emission: Double = 0.0
) : Serializable