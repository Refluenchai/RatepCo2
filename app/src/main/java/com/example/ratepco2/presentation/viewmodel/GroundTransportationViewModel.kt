package com.example.ratepco2.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratepco2.data.repository.CarCarbonRepository
import com.example.ratepco2.data.repository.MotorBikeCarbonRepository
import com.example.ratepco2.data.repository.PublicTransitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroundTransportationViewModel @Inject constructor(
    private val carCarbonRepository: CarCarbonRepository,
    private val motorBikeCarbonRepository: MotorBikeCarbonRepository,
    private val publicTransitRepository: PublicTransitRepository) :
    ViewModel() {

    val carCarbonEquivalentLiveData: MutableLiveData<Double> = MutableLiveData()
    val motorBikeCarbonEquivalentLiveData: MutableLiveData<Double> = MutableLiveData()
    val publicTransitCarbonEquivalentLiveData: MutableLiveData<Double> = MutableLiveData()

    fun getCarCarbonEquivalent(distance: Double, type: String) = viewModelScope.launch {
        carCarbonRepository.getCarCarbon(distance, type) { carCarbonEquivalent ->
            carCarbonEquivalentLiveData.value = carCarbonEquivalent.toDouble()
        }
    }

    fun getMotorBikeCarbonEquivalent(distance: Double, type: String) = viewModelScope.launch {
        motorBikeCarbonRepository.getMotorBikeCarbon(distance, type) { motorBikeCarbonEquivalent ->
            motorBikeCarbonEquivalentLiveData.value = motorBikeCarbonEquivalent.toDouble()
        }
    }

    fun getPublicTransitCarbonEquivalent(distance: Double, type: String) = viewModelScope.launch {
        publicTransitRepository.getPublicTransitCarbon(distance, type) { publicTransitCarbonEquivalent ->
            publicTransitCarbonEquivalentLiveData.value = publicTransitCarbonEquivalent.toDouble()
        }
    }
}