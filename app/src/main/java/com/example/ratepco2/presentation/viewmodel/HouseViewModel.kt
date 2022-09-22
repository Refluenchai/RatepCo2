package com.example.ratepco2.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratepco2.data.repository.EnergyCarbonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(private val energyCarbonRepository: EnergyCarbonRepository) :
    ViewModel() {
    val energyCarbonEquivalentLiveData: MutableLiveData<Double> = MutableLiveData()
    val gasCarbonEquivalentLiveData: MutableLiveData<Double> = MutableLiveData()

    fun getEnergyCarbonEquivalent(consumption: Double) = viewModelScope.launch {
        energyCarbonRepository.getCleanEnergyCarbon(consumption) { carbonEquivalent ->
            if (carbonEquivalent != "-1") energyCarbonEquivalentLiveData.value = carbonEquivalent.toDouble()
        }
    }

    fun getGasCarbonEquivalent(cylinders: Int) {
        gasCarbonEquivalentLiveData.value = cylinders * 31.5 * 0.001102
    }
}