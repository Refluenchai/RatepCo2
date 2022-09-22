package com.example.ratepco2.data.repository

interface MotorBikeCarbonRepository {
    fun getMotorBikeCarbon(distance: Double, type: String, carbonCallback: (carbonEquivalent: String) -> Unit)
}