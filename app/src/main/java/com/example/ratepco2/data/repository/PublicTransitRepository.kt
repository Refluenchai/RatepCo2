package com.example.ratepco2.data.repository

interface PublicTransitRepository {
    fun getPublicTransitCarbon(distance: Double, type: String, carbonCallback: (carbonEquivalent: String) -> Unit)
}