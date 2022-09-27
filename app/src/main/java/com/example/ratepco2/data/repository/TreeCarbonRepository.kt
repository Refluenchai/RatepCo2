package com.example.ratepco2.data.repository

interface TreeCarbonRepository {
    fun getNumberOfTrees(weight: Double, unit: String, treesCallback: (treesCallback: String) -> Unit)
}