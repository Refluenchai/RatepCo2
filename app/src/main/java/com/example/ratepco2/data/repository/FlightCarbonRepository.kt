package com.example.ratepco2.data.repository

interface FlightCarbonRepository {
    fun getFlightCarbon(distance: Double, type: String, carbonCallback: (carbonEquivalent: String) -> Unit)
}