package com.example.ratepco2.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratepco2.data.repository.FlightCarbonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirTravelViewModel @Inject constructor(
    private val flightCarbonRepository: FlightCarbonRepository) : ViewModel() {
    val flightCarbonEquivalentLiveData: MutableLiveData<Double> = MutableLiveData()

    fun getFlightCarbonEquivalent(distance: Double, type: String) = viewModelScope.launch {
        flightCarbonRepository.getFlightCarbon(distance, type) { carCarbonEquivalent ->
            flightCarbonEquivalentLiveData.value = carCarbonEquivalent.toDouble()
        }
    }
}