package com.example.ratepco2.data.repository

interface EnergyCarbonRepository {
    fun getCleanEnergyCarbon(consumption: Double, carbonCallback: (carbonEquivalent: String) -> Unit)
}