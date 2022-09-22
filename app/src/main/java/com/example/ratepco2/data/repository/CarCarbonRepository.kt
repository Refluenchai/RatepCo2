package com.example.ratepco2.data.repository

interface CarCarbonRepository {
    fun getCarCarbon(distance: Double, type: String, carbonCallback: (carbonEquivalent: String) -> Unit)
}