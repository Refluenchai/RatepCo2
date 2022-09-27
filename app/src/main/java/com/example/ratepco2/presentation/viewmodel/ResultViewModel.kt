package com.example.ratepco2.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratepco2.data.repository.TreeCarbonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val treeCarbonRepository: TreeCarbonRepository) :
    ViewModel() {

    val numberOfTreesLiveData: MutableLiveData<Double> = MutableLiveData()

    fun getNumberOfTrees(weight: Double, unit: String = "kg") = viewModelScope.launch {
        treeCarbonRepository.getNumberOfTrees(weight, unit) { numberOfTrees ->
            numberOfTreesLiveData.value = numberOfTrees.toDouble()
        }
    }
}